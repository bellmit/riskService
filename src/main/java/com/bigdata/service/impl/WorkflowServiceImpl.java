package com.bigdata.service.impl;

import cn.com.bsfit.frms.obj.AuditResult;
import cn.com.bsfit.frms.obj.CreditResult;
import cn.com.bsfit.frms.obj.GroupObject;
import cn.com.bsfit.frms.obj.GroupResult;
import com.alibaba.fastjson.JSON;
import com.bigdata.dao.WorkflowDataLogDao;
import com.bigdata.model.AccessAudit;
import com.bigdata.model.WorkflowDataLog;
import com.bigdata.model.WorkflowInstance;
import com.bigdata.service.WorkflowService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.List;

/**
 * Desciption
 * Create By  li.bo
 * CreateTime 2017/10/23 15:07
 * UpdateTime 2017/10/23 15:07
 */
@Service("workflowService")
public class WorkflowServiceImpl implements WorkflowService {

    @Autowired
    WorkflowDataLogDao workflowDataLogDao;

    @Override
    public AccessAudit getAccessAudit(WorkflowInstance workflowInstance) {

        if (workflowInstance == null || StringUtils.isEmpty(workflowInstance.getId())) {
            return null;
        }

        // 决策流解析类
        AccessAudit accessAudit = new AccessAudit();
        // 获取决策流实例id
        String flowId = workflowInstance.getId();
        accessAudit.setId(flowId);
        accessAudit.setUserId(workflowInstance.getUserId());

        // 根据实例id匹配每个节点的信息
        List<WorkflowDataLog> workflowDataLogs = Lists.newArrayList();
        try {
            workflowDataLogs = workflowDataLogDao.getWorkflowDataLogsByFlowId(flowId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (workflowDataLogs == null || workflowDataLogs.size() < 1) {
            return null;
        }
        // 解析节点
        for (WorkflowDataLog workflowDataLog : workflowDataLogs) {
            if (workflowDataLog.getExeIndex() == 1 && workflowDataLog.getDataType() == 2) {
                // 强规则
                AuditResult auditResult = JSON.parseObject(workflowDataLog.getJsonData(), AuditResult.class);
                accessAudit.setStrongAuditResult(auditResult);
                accessAudit.setStrongRuleCnt(auditResult.getRisks().size());
            } else if (workflowDataLog.getExeIndex() == 2 && workflowDataLog.getDataType() == 2) {
                // 中规则
                AuditResult auditResult = JSON.parseObject(workflowDataLog.getJsonData(), AuditResult.class);
                accessAudit.setMiddleAuditResult(auditResult);
                accessAudit.setMiddleRuleCnt(auditResult.getRisks().size());
            } else if (workflowDataLog.getExeIndex() == 3 && workflowDataLog.getDataType() == 2) {
                // 弱规则
                AuditResult auditResult = JSON.parseObject(workflowDataLog.getJsonData(), AuditResult.class);
                accessAudit.setWeakAuditResult(auditResult);
                accessAudit.setWeakRuleCnt(auditResult.getRisks().size());
            } else if (workflowDataLog.getExeIndex() == 5 && workflowDataLog.getDataType() == 2) {
                // 授信结果
                accessAudit.setCreditResult(JSON.parseObject(workflowDataLog.getJsonData(), CreditResult.class));
            } else if (workflowDataLog.getExeIndex() == 7 && workflowDataLog.getDataType() == 1) {
                // 评分卡
                accessAudit.setGroupObject(JSON.parseObject(workflowDataLog.getJsonData(), GroupObject.class));
            } else if (workflowDataLog.getExeIndex() == 7 && workflowDataLog.getDataType() == 2) {
                // 准入策略结果
                GroupResult groupResult = JSON.parseObject(workflowDataLog.getJsonData(), GroupResult.class);
                accessAudit.setGroupPolicy(groupResult.getGroupPolicy());
            }
        }
        return accessAudit;
    }
}

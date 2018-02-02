package com.bigdata.service.impl;

import com.bigdata.dao.WorkflowDataLogDao;
import com.bigdata.model.WorkflowDataLog;
import com.bigdata.service.WorkflowDataLogService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Desciption
 * Create By  li.bo
 * CreateTime 2017/10/23 13:43
 * UpdateTime 2017/10/23 13:43
 */
@Service("workflowDataLogService")
public class WorkflowDataLogServiceImpl implements WorkflowDataLogService {

    @Autowired
    private WorkflowDataLogDao workflowDataLogDao;

    /**
     * 根据流程id获取流程详情
     * @param flowId
     * @return
     */
    @Override
    public List<WorkflowDataLog> getWorkflowDataLogsByFlowId(String flowId) {

        List<WorkflowDataLog> workflowDataLogs = Lists.newArrayList();
        try {
            workflowDataLogs = workflowDataLogDao.getWorkflowDataLogsByFlowId(flowId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return workflowDataLogs;
    }
}

package com.bigdata.controller;

import com.alibaba.fastjson.JSONObject;
import com.bigdata.model.AccessAudit;
import com.bigdata.model.WorkflowInstance;
import com.bigdata.service.WorkflowInstanceService;
import com.bigdata.service.WorkflowService;
import com.bigdata.util.Enum.ResponseResultEnum;
import com.bigdata.util.ResultBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Desciption 风控决策流
 * Create By  li.bo
 * CreateTime 2017/10/23 15:21
 * UpdateTime 2017/10/23 15:21
 */
@RestController
public class WorkflowController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    WorkflowInstanceService workflowInstanceService;

    @Autowired
    WorkflowService workflowService;

    @RequestMapping(value = "/workflow/{flowId}", method = RequestMethod.GET)
    public ResultBody<? extends Object> getDetail(HttpServletRequest request, HttpServletResponse response, @PathVariable String flowId) {

        // 跨域请求处理
        response.addHeader("Access-Control-Allow-Origin", "*");
        /*JSONObject obj = null;
        try {
            obj = RequestJsonUtils.getRequestJsonString(request);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResultBody("0002", "网络出错，请重试！", "");
        }

        if (obj == null || StringUtils.isEmpty(obj.getString("flowId"))) {
            return new ResultBody("0003", "参数错误！", "");
        }

        String flowId = obj.getString("flowId");*/

        if (StringUtils.isEmpty(flowId)) {
            return new ResultBody(ResponseResultEnum.PARAM_ERROR.getFeatureType(), ResponseResultEnum.PARAM_ERROR.getDescription(), "");
        }

        ResultBody<? extends Object> resultBody = null;
        // 根据flowId获取流程实例
        WorkflowInstance workflowInstance = workflowInstanceService.findOneById(flowId);
        if (workflowInstance == null) {
            resultBody = new ResultBody(ResponseResultEnum.NOT_FOUND.getFeatureType(), ResponseResultEnum.NOT_FOUND.getDescription(), "");
            return resultBody;
        }

        // 根据流程实例获取决策流详情
        AccessAudit accessAudit = workflowService.getAccessAudit(workflowInstance);
        if (accessAudit == null) {
            resultBody = new ResultBody(ResponseResultEnum.NOT_FOUND.getFeatureType(), ResponseResultEnum.NOT_FOUND.getDescription(), "");
            return resultBody;
        }

        // 封装返回值
        JSONObject dataObj = new JSONObject();
        dataObj.put("groupObject", accessAudit.getGroupObject());
        dataObj.put("strongRules", accessAudit.getStrongAuditResult().getRisks());
        dataObj.put("middleRules", accessAudit.getMiddleAuditResult().getRisks());
        dataObj.put("weakRules", accessAudit.getWeakAuditResult().getRisks());
        dataObj.put("creditResult", accessAudit.getCreditResult().getCredits());
        dataObj.put("groupPolicy", accessAudit.getGroupPolicy().getName());

        resultBody = new ResultBody(ResponseResultEnum.SUCCESS.getFeatureType(), ResponseResultEnum.SUCCESS.getDescription(), dataObj);

        return resultBody;
    }
}

package com.bigdata.controller;

import com.bigdata.model.AccessAudit;
import com.bigdata.model.WorkflowInstance;
import com.bigdata.service.WorkflowInstanceService;
import com.bigdata.service.WorkflowService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Desciption
 * Create By  li.bo
 * CreateTime 2017/7/26 17:00
 * UpdateTime 2017/7/26 17:00
 */
@Controller
@RequestMapping("/workflowInstance")
public class WorkflowInstanceController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    WorkflowInstanceService workflowInstanceService;

    @Autowired
    WorkflowService workflowService;

    @RequestMapping(value = "/one", method = RequestMethod.GET)
    public String getOne(Model model) {

        WorkflowInstance workflowInstance = new WorkflowInstance();
        try {
            workflowInstance = workflowInstanceService.findOneById("32b2fb4b-bae7-427f-8ef1-a5d838fb745f");
            System.out.println(workflowInstance.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        AccessAudit accessAudit = workflowService.getAccessAudit(workflowInstance);
        logger.info("决策流结果: "+ accessAudit.toString());
        logger.info("分组："+ accessAudit.getGroupObject());
        //强规则明细
        logger.info("强规则明细:"+ accessAudit.getStrongAuditResult().getRisks());
        //中规则明细
        logger.info("中规则明细："+ accessAudit.getMiddleAuditResult().getRisks());
        //弱规则明细
        logger.info("弱规则明细:"+ accessAudit.getWeakAuditResult().getRisks());
        //评分卡明细
        logger.info("评分卡明细："+ accessAudit.getCreditResult().getCredits());
        model.addAttribute("workflowInstance", workflowInstance);

        return "workflowInstance";
    }
}
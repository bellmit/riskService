package com.bigdata.service;


import com.bigdata.model.AccessAudit;
import com.bigdata.model.WorkflowInstance;

/**
 * Desciption
 * Create By  li.bo
 * CreateTime 2017/10/23 15:06
 * UpdateTime 2017/10/23 15:06
 */
public interface WorkflowService {

    AccessAudit getAccessAudit(WorkflowInstance workflowInstance);
}

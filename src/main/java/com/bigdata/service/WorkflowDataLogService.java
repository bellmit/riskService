package com.bigdata.service;


import com.bigdata.model.WorkflowDataLog;

import java.util.List;

/**
 * Desciption 决策流节点详情
 * Create By  li.bo
 * CreateTime 2017/10/23 13:42
 * UpdateTime 2017/10/23 13:42
 */
public interface WorkflowDataLogService {
    List<WorkflowDataLog> getWorkflowDataLogsByFlowId(String flowId);
}

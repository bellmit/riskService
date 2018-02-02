package com.bigdata.dao;

import com.bigdata.model.WorkflowDataLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Desciption
 * Create By  li.bo
 * CreateTime 2017/10/23 13:34
 * UpdateTime 2017/10/23 13:34
 */
public interface WorkflowDataLogDao {

    /**
     * 根据流程id查询决策流节点集
     * @param flowId
     * @return
     */
    public List<WorkflowDataLog> getWorkflowDataLogsByFlowId(@Param("flowId") String flowId) throws Exception;
}

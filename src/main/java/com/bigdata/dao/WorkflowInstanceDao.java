package com.bigdata.dao;

import com.bigdata.model.WorkflowInstance;
import org.apache.ibatis.annotations.Param;

/**
 * Desciption 流程实例
 * Create By  li.bo
 * CreateTime 2017/10/22 16:21
 * UpdateTime 2017/10/22 16:21
 */
public interface WorkflowInstanceDao {

    public WorkflowInstance findOneById(@Param("id") String id) throws Exception;
}

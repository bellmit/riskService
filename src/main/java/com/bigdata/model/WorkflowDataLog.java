package com.bigdata.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * Desciption 决策流节点详情
 * Create By  li.bo
 * CreateTime 2017/10/22 15:58
 * UpdateTime 2017/10/22 15:58
 */
@Accessors(chain = true)
@NoArgsConstructor
@Getter
@Setter
@ToString
public class WorkflowDataLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private String id;

    /**
     * 流程主键（对应workflow_instance表ID）
     */
    private String wInsId;

    /**
     * 执行顺序
     */
    private int exeIndex;

    /**
     * 任务节点ID
     */
    private String taskId;

    /**
     * 任务节点名称
     */
    private String taskName;

    /**
     * 数据类型（1-输入参数，2-输出参数）
     */
    private int dataType;

    /**
     * 输入、输出数据JSON
     */
    private String jsonData;

    private Date createTime;
}

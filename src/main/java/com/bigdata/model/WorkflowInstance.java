package com.bigdata.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * Desciption 流程实例
 * Create By  li.bo
 * CreateTime 2017/10/22 15:59
 * UpdateTime 2017/10/22 15:59
 */
@Accessors(chain = true)
@NoArgsConstructor
@Getter
@Setter
@ToString
public class WorkflowInstance implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识，主键
     */
    private String id;

    /**
     * 流水号(由业务系统传递)
     */
    private String flowId;

    /**
     * 客户号
     */
    private String cusId;

    /**
     * 订单号
     */
    private String orderId;

    /**
     * 用户号
     */
    private String userId;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 流程ID（此实例运行版本的流程编号）
     */
    private String processId;

    /**
     * 流程ProcessKey
     */
    private String processKey;

    /**
     * 流程名称
     */
    private String processName;

    /**
     * 流程实例ID
     */
    private String instanceId;

    /**
     * 当前节点名称
     */
    private String taskName;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 流程调用方式（1-同步，2-异步）
     */
    private int callType;

    /**
     * 状态(1-运行中，2-异常结束，3-异常，9-正常结束)
     */
    private int status;

    /**
     * 状态信息
     */
    private String statusMsg;

    /**
     * 回调次数
     */
    private int callbackTimes;

    /**
     * 回调状态(1-成功，0-失败)
     */
    private int callbackStatus;

    /**
     * 探头参数
     */
    private String auditParam;
}

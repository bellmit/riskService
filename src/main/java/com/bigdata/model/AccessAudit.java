package com.bigdata.model;

import cn.com.bsfit.frms.obj.AuditResult;
import cn.com.bsfit.frms.obj.CreditResult;
import cn.com.bsfit.frms.obj.GroupObject;
import cn.com.bsfit.frms.obj.GroupPolicy;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * Desciption 准入决策结果
 * Create By  li.bo
 * CreateTime 2017/12/24 14:59
 * UpdateTime 2017/12/24 14:59
 */
@Accessors(chain = true)
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AccessAudit {

    /**
     * 唯一编号
     */
    private String id;
    /**
     * 用户号
     */
    private String userId;
    /**
     * 电话号码
     */
    private String phoneNo;
    /**
     * 入参
     */
    private GroupObject groupObject;
    /**
     * 强规则数量
     */
    private Integer strongRuleCnt;
    /**
     * 强规则明细
     */
    private AuditResult strongAuditResult;
    /**
     * 中规则数量
     */
    private Integer middleRuleCnt;
    /**
     * 中规则明细
     */
    private AuditResult middleAuditResult;
    /**
     * 弱规则数量
     */
    private Integer weakRuleCnt;
    /**
     * 弱规则明细
     */
    private AuditResult weakAuditResult;
//    /**
//     * 评分卡分值
//     */
//    private Double creditScore;
    /**
     * 评分卡明细
     */
    private CreditResult creditResult;
    /**
     * 准入结果
     */
    private String accessResult;

    /**
     * 准入策略结果
     */
    private GroupPolicy groupPolicy;
}

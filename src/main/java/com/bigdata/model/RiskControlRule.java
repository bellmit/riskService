package com.bigdata.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * Desciption 风控规则
 * Create By  li.bo
 * CreateTime 2018/3/19 14:19
 * UpdateTime 2018/3/19 14:19
 */
@NoArgsConstructor
@Accessors(chain = true)
@Setter
@Getter
@ToString
public class RiskControlRule {

    private long id;
    private String rule;            // 规则编号
    private String type;            // 管理类型
    private String subType;         // 子类型
    private String monitorContent;  // 监测内容
    private String threshold;       // 阈值
    private String value;           // 当前指标
    private String unit;            // 单位
}

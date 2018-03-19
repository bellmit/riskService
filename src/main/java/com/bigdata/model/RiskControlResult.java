package com.bigdata.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * Desciption 风控结果
 * Create By  li.bo
 * CreateTime 2018/3/19 14:24
 * UpdateTime 2018/3/19 14:24
 */
@NoArgsConstructor
@Accessors(chain = true)
@Setter
@Getter
@ToString
public class RiskControlResult {

    private long id;
    private String result;  // 风控结果
    private String rule;    // 触发规则结果
    private int year;       // 年份
    private int month;      // 月份
    private Date createTime;// 创建时间
}

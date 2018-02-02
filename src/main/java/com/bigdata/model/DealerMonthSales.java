package com.bigdata.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * Desciption 经销商每月销售金额
 * Create By  li.bo
 * CreateTime 2017/12/24 14:59
 * UpdateTime 2017/12/24 14:59
 */
@Accessors(chain = true)
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DealerMonthSales {

    private int year;
    private int month;
    private BigDecimal amount;
}

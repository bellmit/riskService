package com.bigdata.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * Desciption
 * Create By  li.bo
 * CreateTime 2018/3/15 9:27
 * UpdateTime 2018/3/15 9:27
 */
@NoArgsConstructor
@Accessors(chain = true)
@Setter
@Getter
@ToString
public class SBBlankResult {

    private Long id;
    private String code;
    private String dealer_name;
    private String dealer_area;
    private String dealer;
    private double last_year_amount;
    private double last_year_avg_amount;
    private double last_year_out_amount;
    private double last_year_out_avg_amount;
    private double limit;
    private double trade_amount;
    private int payment_numbers;
}

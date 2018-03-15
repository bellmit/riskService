package com.bigdata.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * Desciption
 * Create By  li.bo
 * CreateTime 2018/3/15 10:43
 * UpdateTime 2018/3/15 10:43
 */
@NoArgsConstructor
@Accessors(chain = true)
@Setter
@Getter
@ToString
public class SBBlankDealer {

    private String dealer;
    private String area;
    private double limit;
    private double trade_amount;
    private int payment_numbers;
}

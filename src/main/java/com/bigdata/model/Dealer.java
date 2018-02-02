package com.bigdata.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * Desciption 经销商信息
 * Create By  li.bo
 * CreateTime 2017/12/24 14:59
 * UpdateTime 2017/12/24 14:59
 */
@Accessors(chain = true)
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Dealer {

    private Long id;
    private String phone;
    private String province;
    private String city;
    private BigDecimal la;
    private BigDecimal lo;
    private int shopNum;
    private int redStarShopNum;
    private int dealerNum;
}

package com.bigdata.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * Desciption
 * Create By  li.bo
 * CreateTime 2017/12/24 16:25
 * UpdateTime 2017/12/24 16:25
 */
@Accessors(chain = true)
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProvinceDealer {

    private String province;
    private String city;
    private int dealerNum;
    private int shopNum;
    private int redStarShopNum;
}

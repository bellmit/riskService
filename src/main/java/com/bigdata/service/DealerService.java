package com.bigdata.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * Desciption
 * Create By  li.bo
 * CreateTime 2017/12/24 15:21
 * UpdateTime 2017/12/24 15:21
 */
public interface DealerService {
    JSONObject getAllDealer();

    JSONObject getDealerShopByProvince();

    JSONObject getDealerShopByCity();

    JSONArray getAmountByYearAndMonth();
}

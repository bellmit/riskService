package com.bigdata.service;


import com.bigdata.model.SBOrder;

import java.util.List;
import java.util.Map;

/**
 * Desciption
 * Create By  li.bo
 * CreateTime 2018/1/31 13:50
 * UpdateTime 2018/1/31 13:50
 */
public interface SBOrderService {
    List<SBOrder> listOrders(Map<String, Object> map);

    Integer countOrders(Map<String, Object> map);
}

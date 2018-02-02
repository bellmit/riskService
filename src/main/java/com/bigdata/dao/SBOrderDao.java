package com.bigdata.dao;


import com.bigdata.model.SBOrder;

import java.util.List;
import java.util.Map;

/**
 * Desciption
 * Create By  li.bo
 * CreateTime 2018/1/24 11:13
 * UpdateTime 2018/1/24 11:13
 */
public interface SBOrderDao {

    List<SBOrder> getList(Map<String, Object> map);

    Integer getCount(Map<String, Object> map);
}

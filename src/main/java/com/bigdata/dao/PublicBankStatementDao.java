package com.bigdata.dao;


import com.bigdata.model.SBOrder;

import java.util.List;
import java.util.Map;

/**
 * Desciption 工厂对公银行流水Dao
 * Create By  li.bo
 * CreateTime 2018/1/24 11:13
 * UpdateTime 2018/1/24 11:13
 */
public interface PublicBankStatementDao {

    Long getLastMonthCreditAmount(Map<String, Object> map);
}

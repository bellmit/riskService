package com.bigdata.dao;


import java.util.Map;

/**
 * Desciption 个人对私银行流水Dao
 * Create By  li.bo
 * CreateTime 2018/1/24 11:13
 * UpdateTime 2018/1/24 11:13
 */
public interface PrivateBankStatementDao {

    // X个月前，屏蔽关键字，法人对私回款金额
    Long getLastMonthCreditAmount(Map<String, Object> map);
}

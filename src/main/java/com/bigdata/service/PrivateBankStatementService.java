package com.bigdata.service;

import java.util.Map;

/**
 * Desciption
 * Create By  li.bo
 * CreateTime 2018/3/12 10:11
 * UpdateTime 2018/3/12 10:11
 */
public interface PrivateBankStatementService {
    Long getLastMonthCreditAmount(Map<String, Object> map);
}

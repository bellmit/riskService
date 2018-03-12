package com.bigdata.service;

import java.util.Map;

/**
 * Desciption
 * Create By  li.bo
 * CreateTime 2018/3/12 13:44
 * UpdateTime 2018/3/12 13:44
 */
public interface PublicBankStatementService {
    Long getLastMonthCreditAmount(Map<String, Object> map);
}

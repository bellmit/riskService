package com.bigdata.service.impl;

import com.bigdata.dao.PublicBankStatementDao;
import com.bigdata.service.PublicBankStatementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Desciption
 * Create By  li.bo
 * CreateTime 2018/3/12 13:45
 * UpdateTime 2018/3/12 13:45
 */
@Service("publicBankStatementService")
public class PublicBankStatementServiceImpl implements PublicBankStatementService {

    @Autowired
    private PublicBankStatementDao publicBankStatementDao;

    @Override
    public Long getLastMonthCreditAmount(Map<String, Object> map) {

        return publicBankStatementDao.getLastMonthCreditAmount(map);
    }
}

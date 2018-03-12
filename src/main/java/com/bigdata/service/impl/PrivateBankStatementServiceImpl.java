package com.bigdata.service.impl;

import com.bigdata.dao.PrivateBankStatementDao;
import com.bigdata.service.PrivateBankStatementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Desciption 对私银行流水服务层
 * Create By  li.bo
 * CreateTime 2018/3/12 10:11
 * UpdateTime 2018/3/12 10:11
 */
@Service("privateBankStatementService")
public class PrivateBankStatementServiceImpl implements PrivateBankStatementService {

    @Autowired
    private PrivateBankStatementDao privateBankStatementDao;

    @Override
    public Long getLastMonthCreditAmount(Map<String, Object> map) {

        return privateBankStatementDao.getLastMonthCreditAmount(map);
    }
}

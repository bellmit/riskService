package com.bigdata.service.impl;

import com.bigdata.dao.RiskControlDao;
import com.bigdata.model.RiskControlResult;
import com.bigdata.model.RiskControlRule;
import com.bigdata.service.RiskControlService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Desciption
 * Create By  li.bo
 * CreateTime 2018/3/19 15:00
 * UpdateTime 2018/3/19 15:00
 */
@Service("riskControlService")
public class RiskControlServiceImpl implements RiskControlService {

    @Autowired
    private RiskControlDao riskControlDao;

    @Override
    public int saveOneResult(RiskControlResult riskControlResult) {
        return riskControlDao.saveOneResult(riskControlResult);
    }

    @Override
    public List<RiskControlRule> getAllRules() {
        return riskControlDao.getAllRules();
    }

    @Override
    public RiskControlResult getLastestResult() {
        return riskControlDao.getLastestResult();
    }
}

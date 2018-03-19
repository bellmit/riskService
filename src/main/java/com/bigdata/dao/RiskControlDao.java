package com.bigdata.dao;

import com.bigdata.model.RiskControlResult;
import com.bigdata.model.RiskControlRule;

import java.util.List;

/**
 * Desciption 风控相关DAO
 * Create By  li.bo
 * CreateTime 2018/3/19 14:31
 * UpdateTime 2018/3/19 14:31
 */
public interface RiskControlDao {

    int saveOneResult(RiskControlResult riskControlResult);

    List<RiskControlRule> getAllRules();

    RiskControlResult getLastestResult();
}

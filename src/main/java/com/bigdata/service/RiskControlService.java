package com.bigdata.service;

import com.bigdata.model.RiskControlResult;
import com.bigdata.model.RiskControlRule;

import java.util.List;

/**
 * Desciption 风控相关service
 * Create By  li.bo
 * CreateTime 2018/3/19 14:59
 * UpdateTime 2018/3/19 14:59
 */
public interface RiskControlService {
    int saveOneResult(RiskControlResult riskControlResult);

    List<RiskControlRule> getAllRules();

    RiskControlResult getLastestResult();
}

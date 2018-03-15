package com.bigdata.service.impl;

import com.bigdata.dao.SBBlankDealerDao;
import com.bigdata.dao.SBBlankResultDao;
import com.bigdata.dao.SBOrderDao;
import com.bigdata.model.SBBlankDealer;
import com.bigdata.model.SBBlankResult;
import com.bigdata.service.SBBlankResultService;
import com.bigdata.util.BigDecimalUtils;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Desciption
 * Create By  li.bo
 * CreateTime 2018/3/15 9:48
 * UpdateTime 2018/3/15 9:48
 */
@Service("sbBlankResultService")
public class SBBlankResultServiceImpl implements SBBlankResultService {

    @Autowired
    private SBBlankResultDao sbBlankResultDao;

    @Autowired
    private SBOrderDao sbOrderDao;

    @Autowired
    private SBBlankDealerDao sbBlankDealerDao;

    @Override
    public void save() {

        List<SBBlankResult> blankResults = sbBlankResultDao.getAll();
        for (SBBlankResult blankResult : blankResults) {
            Map<String, Object> paramsMap = Maps.newHashMap();
            paramsMap.put("year", "2017");
            paramsMap.put("dealerName", blankResult.getDealer_name());

            try {
                Long last_year_amount = sbOrderDao.getAmountForYear(paramsMap);
                blankResult.setLast_year_amount(last_year_amount);
                blankResult.setLast_year_avg_amount(BigDecimalUtils.div(last_year_amount, 12, 2));

                Long last_year_out_amount = sbOrderDao.getCkAmountForYear(paramsMap);
                blankResult.setLast_year_out_amount(last_year_out_amount);
                blankResult.setLast_year_out_avg_amount(BigDecimalUtils.div(last_year_out_amount, 12, 2));

                SBBlankDealer sbBlankDealer = sbBlankDealerDao.getOne(blankResult.getDealer());
                if (sbBlankDealer != null) {
//                    blankResult.setLimit(sbBlankDealer.getLimit());
                    blankResult.setTrade_amount(sbBlankDealer.getTrade_amount());
                    blankResult.setPayment_numbers(sbBlankDealer.getPayment_numbers());
                }

                sbBlankResultDao.saveOne(blankResult);
            } catch (Exception e) {
                e.printStackTrace();

            }
        }
    }
}

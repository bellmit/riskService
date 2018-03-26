package com.bigdata.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bigdata.model.RiskControlData;
import com.bigdata.service.PrivateBankStatementService;
import com.bigdata.service.PublicBankStatementService;
import com.bigdata.service.RiskControlDataService;
import com.bigdata.service.SBOrderService;
import com.bigdata.util.BigDecimalUtils;
import com.bigdata.util.DateUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Desciption 风控请求数据探头包装
 * Create By  li.bo
 * CreateTime 2018/3/12 14:33
 * UpdateTime 2018/3/12 14:33
 */
@Service("riskControlDataService")
public class RiskControlDataServiceImpl implements RiskControlDataService {

    // 本月
    public static final int THIS_MONTH = -1;

    @Autowired
    private PrivateBankStatementService privateBankStatementService;

    @Autowired
    private PublicBankStatementService publicBankStatementService;

    @Autowired
    private SBOrderService sbOrderService;

    @Override
    public JSONObject init() {

        RiskControlData params = new RiskControlData();

        // 基本信息
        params.setFrms_flow_id(UUID.randomUUID().toString());
        params.setFrms_customer_id("homefax");
        params.setFrms_user_id("syh");
        params.setFrms_biz_code("PAY.REG");

        // 经营风险A
        params.setFrms_payback_decrease_rate(frms_payback_decrease_rate());// 本月回款下降比例
        params.setFrms_lastY_amount_decrease_rate(frms_lastY_amount_decrease_rate());// 本月经销商订单金额较上年度同期下降比例
        params.setFrms_lastM_decrease_rate(frms_lastM_decrease_rate());// 本月经销商订单金额较上月下降比例
        params.setFrms_payback_amount(frms_payback_amount());// 本月回款金额
        params.setFrms_lastS_amount_decrease_rate(0);// 本季度经销商累计订单金额较上年度同期下降比例
        params.setFrms_refund_amount_rate(0);// 本月经销商订单退货金额占当月订单总额的比率（订单退货率）
        // 经营风险B
        params.setFrms_last3M_avg_payback_amount(frms_last3M_avg_payback_amount());// 最近三个月公司、法人账户月平均销售回款入金额度
        params.setFrms_3M_payback_amount(frms_last3M_avg_payback_amount());// 连续出现3个月出现公司、法人账户累计销售回款入金额度
        params.setFrms_3M_amount_decrease_rate(frms_3M_amount_decrease_rate());// 连续3个月出现经销商月度订单金额较上月下降
        params.setFrms_last3M_avg_amount(frms_last3M_avg_amount());// 近3个月经销商月平均订单金额
        params.setFrms_last3M_refund_increase_rate(0);// 连续3个月月出现经销商订单退货率较上月增长比率
        // 财务风险A
        params.setFrms_net_asset_month_decrease_rate(0);// 公司净资产较上月下降比例
        params.setFrms_company_debt_rate(BigDecimalUtils.div(108585492L, 331237819L, 2) * 100);// 公司资产负债率
        params.setFrms_company_gross_margin(BigDecimalUtils.div(117742045L, 732071329L, 2) * 100);// 公司毛利润率
        params.setFrms_company_net_profit_rate(BigDecimalUtils.div(57653541L, 732071329L, 2) * 100);// 净利润率
        params.setFrms_receivables_turnover_days(BigDecimalUtils.div(BigDecimalUtils.add(44920879L, 31881817L) / 2 * 360, 732071329L, 2));// 公司应收账款周转天数
        params.setFrms_stock_turnover_days(BigDecimalUtils.div(BigDecimalUtils.add(188718629L, 144589746L) / 2 * 360, 613794926L, 2));// 存货周转天数
        params.setFrms_main_business_amount_mount_decrease_rate(0);// 本月公司主营业务收入金额较上月下降比例
        params.setFrms_last_month_result_a(1);// 上月通过
        // 财务风险B
        params.setFrms_net_asset_year_decrease_rate(BigDecimalUtils.div(BigDecimalUtils.sub(455147973L, 331237819L), 455147973L, 2) * 100);// 公司年末净资产余额较年初下降比例
        params.setFrms_quick_rate(BigDecimalUtils.div(BigDecimalUtils.sub(303421933L, 144589746L), 108585492L, 2) * 100);// 公司速动比率
        params.setFrms_liquidity_rate(BigDecimalUtils.div(303421933L, 108585492L, 2) * 100);// 流动比率
        params.setFrms_main_business_amount_season_decrease_rate(0);// 本季度公司主营业务收入金额较上年同期下降比例
        params.setFrms_season_net_profit_amount(1);// 本季度公司累计净利润金额
        params.setFrms_is_loss(0);// 经我司评估本年度公司净利润将处于亏损状态
        params.setFrms_last_month_result_b(1);// 上月通过
        // 法律信用风险
        params.setFrms_lawsuit_net_asset_rate(0);// 诉讼金额比例
        params.setFrms_is_overdue(0);// 借款人、借款企业贷款尚处于逾期状态
        // 其他风险
        params.setFrms_is_reject(0);

        return (JSONObject) JSON.toJSON(params);
    }

    // 本月回款下降比例
    private double frms_payback_decrease_rate() {

        Map<String, Object> map = Maps.newHashMap();
        map.put("userId", "syh");
        map.put("factoryId", "syh");
        List<String> keywords = Lists.newArrayList();
        keywords.add("森堡家俬");
        keywords.add("松堡王国");
        keywords.add("郭向阳");
        map.put("keywords", keywords);
        map.put("year", DateUtils.getYear(THIS_MONTH));
        map.put("month", DateUtils.getMonth(THIS_MONTH));
        Long thisMonthPrivate = privateBankStatementService.getLastMonthCreditAmount(map);
        Long thisMonthPublic = publicBankStatementService.getLastMonthCreditAmount(map);

        map.put("year", DateUtils.getYear(THIS_MONTH - 1));
        map.put("month", DateUtils.getMonth(THIS_MONTH - 1));
        Long lastMonthPrivate = privateBankStatementService.getLastMonthCreditAmount(map);
        Long lastMonthPublic = publicBankStatementService.getLastMonthCreditAmount(map);

        double result = BigDecimalUtils.div(BigDecimalUtils.sub(lastMonthPrivate.doubleValue() + lastMonthPublic.doubleValue(), thisMonthPrivate.doubleValue() + thisMonthPublic.doubleValue()), lastMonthPrivate.doubleValue() + lastMonthPublic.doubleValue(), 2) * 100;

        return result;
    }

    // 本月经销商订单金额较上年度同期下降比例
    private double frms_lastY_amount_decrease_rate() {

        Map<String, Object> map = Maps.newHashMap();
        // 本月
        map.put("year", DateUtils.getYear(THIS_MONTH));
        map.put("month", DateUtils.getMonth(THIS_MONTH));
        Long thisMonth = sbOrderService.getOrderAmount(map);
        // 上年度同期
        map.put("year", DateUtils.getYear(THIS_MONTH - 12));
        Long lastYearMonth = sbOrderService.getOrderAmount(map);

        double result = BigDecimalUtils.mul(BigDecimalUtils.div(BigDecimalUtils.sub(lastYearMonth.doubleValue(), thisMonth.doubleValue()), lastYearMonth.doubleValue(), 2), 100);

        return result;
    }

    // 本月经销商订单金额较上月下降比例
    private double frms_lastM_decrease_rate() {

        Map<String, Object> map = Maps.newHashMap();
        // 本月
        map.put("year", DateUtils.getYear(THIS_MONTH));
        map.put("month", DateUtils.getMonth(THIS_MONTH));
        Long thisMonth = sbOrderService.getOrderAmount(map);
        // 上月
        map.put("year", DateUtils.getYear(THIS_MONTH - 1));
        map.put("month", DateUtils.getMonth(THIS_MONTH - 1));
        Long lastMonth = sbOrderService.getOrderAmount(map);

        double result = BigDecimalUtils.mul(BigDecimalUtils.div(BigDecimalUtils.sub(lastMonth.doubleValue(), thisMonth.doubleValue()), lastMonth.doubleValue(), 2), 100);

        return result;
    }

    // 本月回款金额
    private double frms_payback_amount() {

        Map<String, Object> map = Maps.newHashMap();
        map.put("userId", "syh");
        map.put("factoryId", "syh");
        List<String> keywords = Lists.newArrayList();
        keywords.add("森堡家俬");
        keywords.add("松堡王国");
        keywords.add("郭向阳");
        map.put("keywords", keywords);
        map.put("year", DateUtils.getYear(THIS_MONTH));
        map.put("month", DateUtils.getMonth(THIS_MONTH));
        Long thisMonthPrivate = privateBankStatementService.getLastMonthCreditAmount(map);
        Long thisMonthPublic = publicBankStatementService.getLastMonthCreditAmount(map);

        double result = BigDecimalUtils.div(BigDecimalUtils.add(thisMonthPrivate.doubleValue(), thisMonthPublic.doubleValue()), 10000, 0);

        return result;
    }

    // TODO

    // TODO

    // 最近三个月公司、法人账户月平均销售回款入金额度
    private double frms_last3M_avg_payback_amount() {

        Map<String, Object> map = Maps.newHashMap();
        map.put("userId", "syh");
        map.put("factoryId", "syh");
        List<String> keywords = Lists.newArrayList();
        keywords.add("森堡家俬");
        keywords.add("松堡王国");
        keywords.add("郭向阳");
        map.put("keywords", keywords);
        map.put("year", DateUtils.getYear(THIS_MONTH));
        map.put("month", DateUtils.getMonth(THIS_MONTH));
        Long thisMonth = privateBankStatementService.getLastMonthCreditAmount(map) + publicBankStatementService.getLastMonthCreditAmount(map);

        map.put("year", DateUtils.getYear(THIS_MONTH - 1));
        map.put("month", DateUtils.getMonth(THIS_MONTH - 1));
        Long lastMonth = privateBankStatementService.getLastMonthCreditAmount(map) + publicBankStatementService.getLastMonthCreditAmount(map);

        map.put("year", DateUtils.getYear(THIS_MONTH - 2));
        map.put("month", DateUtils.getMonth(THIS_MONTH - 2));
        Long thirdMonth = privateBankStatementService.getLastMonthCreditAmount(map) + publicBankStatementService.getLastMonthCreditAmount(map);

        return BigDecimalUtils.div(BigDecimalUtils.div(thisMonth.doubleValue() + lastMonth.doubleValue() + thirdMonth.doubleValue(), 3), 10000, 2);
    }

    // 连续3个月出现经销商月度订单金额较上月下降
    private double frms_3M_amount_decrease_rate() {

        Map<String, Object> map = Maps.newHashMap();
        // 本月
        map.put("year", DateUtils.getYear(THIS_MONTH));
        map.put("month", DateUtils.getMonth(THIS_MONTH));
        Long thisMonth = sbOrderService.getOrderAmount(map);
        // 上月
        map.put("year", DateUtils.getYear(THIS_MONTH - 1));
        map.put("month", DateUtils.getMonth(THIS_MONTH - 1));
        Long lastMonth = sbOrderService.getOrderAmount(map);
        // 上上月
        map.put("year", DateUtils.getYear(THIS_MONTH - 2));
        map.put("month", DateUtils.getMonth(THIS_MONTH - 2));
        Long thirdMonth = sbOrderService.getOrderAmount(map);
        // 上上上月
        map.put("year", DateUtils.getYear(THIS_MONTH - 3));
        map.put("month", DateUtils.getMonth(THIS_MONTH - 3));
        Long fourthMonth = sbOrderService.getOrderAmount(map);

        // 三个月下降率的平均值
        return BigDecimalUtils.div(
                BigDecimalUtils.div(BigDecimalUtils.sub(lastMonth.doubleValue(), thisMonth.doubleValue()), lastMonth.doubleValue(), 2) * 100 +
                BigDecimalUtils.div(BigDecimalUtils.sub(thirdMonth.doubleValue(), lastMonth.doubleValue()), thirdMonth.doubleValue(), 2) * 100 +
                BigDecimalUtils.div(BigDecimalUtils.sub(fourthMonth.doubleValue(), thirdMonth.doubleValue()), fourthMonth.doubleValue(), 2) * 100
        , 3, 0);
    }

    // 近3个月经销商月平均订单金额
    private double frms_last3M_avg_amount() {

        Map<String, Object> map = Maps.newHashMap();
        // 本月
        map.put("year", DateUtils.getYear(THIS_MONTH));
        map.put("month", DateUtils.getMonth(THIS_MONTH));
        Long thisMonth = sbOrderService.getOrderAmount(map);
        // 上月
        map.put("year", DateUtils.getYear(THIS_MONTH - 1));
        map.put("month", DateUtils.getMonth(THIS_MONTH - 1));
        Long lastMonth = sbOrderService.getOrderAmount(map);
        // 上上月
        map.put("year", DateUtils.getYear(THIS_MONTH - 2));
        map.put("month", DateUtils.getMonth(THIS_MONTH - 2));
        Long thirdMonth = sbOrderService.getOrderAmount(map);

        return BigDecimalUtils.div(
                thisMonth.doubleValue() + lastMonth.doubleValue() + thirdMonth.doubleValue(), 3 * 10000, 0
        );
    }

    // TODO
}

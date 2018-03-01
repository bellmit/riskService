package com.bigdata.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bigdata.model.PaginatedResult;
import com.bigdata.service.SBOrderService;
import com.bigdata.util.BigDecimalUtils;
import com.bigdata.util.Enum.PageConstant;
import com.bigdata.util.Enum.ResponseResultEnum;
import com.bigdata.util.HttpConnectionUtils;
import com.bigdata.util.PageUtil;
import com.bigdata.util.ResultBody;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Desciption 深一号决策流调用
 * Create By  li.bo
 * CreateTime 2018/01/26 18:14
 * UpdateTime 2018/01/26 18:14
 */
@RestController
@Slf4j
public class ShenYiHaoController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${flow.shenyihao}")
    private String api_url;

    @Autowired
    private SBOrderService sbOrderService;

    @RequestMapping(value = "/syh", method = RequestMethod.POST)
    public ResultBody<? extends Object> getDetail(HttpServletRequest request, HttpServletResponse response) {

        /* 入参 **/
        JSONObject obj = new JSONObject();
        // 决策流必传参数
        obj.put("frms_flow_id", UUID.randomUUID());
        obj.put("frms_customer_id", "homefax");
        obj.put("frms_user_id", "syh");
        obj.put("frms_biz_code", "PAY.REG");

        // 决策流业务参数
        // 经营风险A
        obj.put("frms_payback_decrease_rate", (47320819-26690781)/47320819*100);// 本月回款下降比例
        obj.put("frms_lastY_amount_decrease_rate", BigDecimalUtils.mul(BigDecimalUtils.div(BigDecimalUtils.sub(25457264L, 26690781L), 25457264L, 2), 100L));// 本月经销商订单金额较上年度同期下降比例
        obj.put("frms_lastM_decrease_rate", BigDecimalUtils.mul(BigDecimalUtils.div(BigDecimalUtils.sub(30827872L,3911773L), 30827872L, 2), 100L));// 本月经销商订单金额较上月下降比例
        obj.put("frms_payback_amount", 47320819/10000);// 本月回款金额
        obj.put("frms_lastS_amount_decrease_rate", BigDecimalUtils.mul(BigDecimalUtils.div(BigDecimalUtils.sub(44235783L, 34739645L), 44235783L, 2), 100L));// 本季度经销商累计订单金额较上年度同期下降比例

        // 经营风险B
        obj.put("frms_last3M_avg_payback_amount", BigDecimalUtils.div(BigDecimalUtils.div(BigDecimalUtils.add(47320819L, 26690781L, 40128981L), 3), 10000, 2));// 最近三个月公司、法人账户月平均销售回款入金额度
        obj.put("frms_3M_payback_amount", BigDecimalUtils.div(BigDecimalUtils.add(47320819L, 26690781L, 40128981L), 10000, 2)); // 连续出现3个月出现公司、法人账户累计销售回款入金额度
        if((30827872-3911773)/30827872*100 >20 && (35717483-30827872)/35717483*100 > 20 && (38123870-35717483)/38123870*100 > 20){
            obj.put("frms_3M_amount_decrease_rate", 20);// 连续3个月出现经销商月度订单金额较上月下降
        }else {
            obj.put("frms_3M_amount_decrease_rate", 0);
        }
        if((30827872+3911773+35717483)/10000/3 < 400){
            obj.put("frms_last3M_avg_amount", 0);// 近3个月经销商月平均订单金额
        }else{
            obj.put("frms_last3M_avg_amount", (30827872+3911773+35717483)/10000/3);
        }

        // 财务风险A
        obj.put("frms_net_asset_month_decrease_rate", 25);//
        obj.put("frms_company_debt_rate", BigDecimalUtils.div(108585492L, 331237819L, 2)*100);// 公司资产负债率
        obj.put("frms_company_gross_margin", BigDecimalUtils.div(117742045L, 732071329L, 2)*100);// 公司毛利润率
        obj.put("frms_company_net_profit_rate", BigDecimalUtils.div(57653541L, 732071329L, 2)*100);// 净利润率
        obj.put("frms_receivables_turnover_days", BigDecimalUtils.div(BigDecimalUtils.add(44920879L, 31881817L)/2*360, 732071329L, 2)); // 公司应收账款周转天数
        obj.put("frms_stock_turnover_days", BigDecimalUtils.div(BigDecimalUtils.add(188718629L, 144589746L)/2*360, 613794926L, 2));// 存货周转天数
        obj.put("frms_main_business_amount_mount_decrease_rate", 0); // 本月公司主营业务收入金额较上月下降比例(本月58425528，上月无)
        obj.put("frms_last_month_result_a", 1);// 上月通过

        // 财务风险B
        obj.put("frms_net_asset_year_decrease_rate", BigDecimalUtils.div(BigDecimalUtils.sub(455147973L, 331237819L), 455147973L, 2) * 100);// 公司年末净资产余额较年初下降比例
//        obj.put("frms_company_debt_rate", 80);// 公司资产负债率
        obj.put("frms_quick_rate", BigDecimalUtils.div(BigDecimalUtils.sub(303421933L, 144589746L), 108585492L, 2) * 100);// 公司速动比率
        obj.put("frms_liquidity_rate", BigDecimalUtils.div(303421933L, 108585492L, 2) * 100);// 流动比率
        obj.put("frms_main_business_amount_season_decrease_rate", 0); // 本季度公司主营业务收入金额较上年同期下降比例(上年度没有)
//        obj.put("frms_season_net_profit_amount", BigDecimalUtils.sub(57653541L, 63973509L)); // 本季度公司累计净利润金额
        obj.put("frms_season_net_profit_amount", 1); // 本季度公司累计净利润金额
        obj.put("frms_is_loss", 0); // 经我司评估本年度公司净利润将处于亏损状态

        obj.put("frms_last_month_result_b", 1);// 上月通过
        // 法律信用风险
        obj.put("frms_lawsuit_net_asset_rate", 0); // 诉讼金额比例
        obj.put("frms_is_overdue", 0); // 借款人、借款企业贷款尚处于逾期状态
        // 其他风险
        obj.put("frms_is_reject", 0);

        // 转发请求到服务端
        String result = HttpConnectionUtils.setConnection(this.api_url, obj);
        JSONObject resultObj = JSONObject.parseObject(result);
        logger.info("返回结果： " + resultObj.toString());

        try {
            // 触发规则
            JSONArray ruleArray = Optional.ofNullable(resultObj)
                    .map(a -> a.getBoolean("success") ? a.getJSONObject("data").getJSONArray("nodeResults") : null)
                    .get();
            List<JSONObject> rules = new ArrayList<>();
            // 正则判断：字符串是否有数字（普通规则）
            Pattern p = Pattern.compile("[0-9]");
            ruleArray.forEach(a -> {
                JSONArray risks = JSON.parseObject(a.toString()).getJSONObject("result").getJSONArray("risks");
                String taskName = JSON.parseObject(a.toString()).getString("taskName");
                risks.forEach(risk -> {
                    JSONObject r = JSON.parseObject(risk.toString());
                    String tmp = r.getString("ruleName");
                    JSONObject rule = new JSONObject();

                    String ruleCode = tmp.substring(tmp.indexOf(":") + 1, tmp.lastIndexOf(":"));
                    Matcher m = p.matcher(ruleCode);
                    if (m.find()) {
                        rule.put("taskName", taskName);
                        rule.put("ruleCode", ruleCode);
                        rule.put("ruleName", tmp.substring(tmp.lastIndexOf(":") + 1, tmp.length()));
                        rules.add(rule);

                    }

                });
            });
            logger.info("rules:{}", rules);

            // 风控结果
            JSONObject res = Optional.ofNullable(resultObj)
                    .map(a -> a.getJSONObject("data").getJSONArray("nodeResults"))
                    .map(a -> a.get(a.size() - 1).toString())
                    .map(a -> JSON.parseObject(a).getJSONObject("result").getJSONArray("items"))
                    .map(a -> a.get(a.size() - 1).toString())
                    .map(JSON::parseObject)
                    .get();
            return new ResultBody(ResponseResultEnum.SUCCESS.getFeatureType(), ResponseResultEnum.SUCCESS.getDescription(), res);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultBody(ResponseResultEnum.RETRY.getFeatureType(), ResponseResultEnum.RETRY.getDescription(), "");
        }

//        return new ResultBody(ResponseResultEnum.SUCCESS.getFeatureType(), ResponseResultEnum.SUCCESS.getDescription(), resultObj.getJSONObject("data"));
    }

    @PostMapping("/syh/order")
    public ResultBody<? extends Object> getOrders(HttpServletRequest request, @RequestBody String paramsString, HttpServletResponse response) {

        JSONObject params = JSON.parseObject(paramsString);
        log.info("params:{}", params);

        int page = PageUtil.parsePage(params.getString("pageString"), PageConstant.PAGE);
        int perPage = PageUtil.parsePerPage(params.getString("perPageString"), PageConstant.PER_PAGE);

        Map<String, Object> map = Maps.newHashMap();
        try {
            map.put("startDate", Optional
                    .ofNullable(params.getJSONArray("rq"))
                    .map(a -> a.size() > 0 ? a.get(0) : "")
                    .get());

            map.put("endDate", Optional
                    .ofNullable(params.getJSONArray("rq"))
                    .map(a -> a.size() > 1 ? a.get(1) : "")
                    .get());
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("ghdw", params.getString("ghdw").replaceAll("\\s*", "")); // 去掉空格
        map.put("page", page);
        map.put("perPage", perPage);

        log.info("map:{}", map);

        PaginatedResult result = new PaginatedResult()
                .setData(sbOrderService.listOrders(map))
                .setCurrentPage(page)
                .setTotal(sbOrderService.countOrders(map))
                .setTotalPage(PageUtil.calculateTotalPage(sbOrderService.countOrders(map), perPage));

        return new ResultBody(ResponseResultEnum.SUCCESS.getFeatureType(), ResponseResultEnum.SUCCESS.getDescription(), result);
    }
}
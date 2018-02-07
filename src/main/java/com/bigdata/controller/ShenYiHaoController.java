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
import java.util.*;

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
        obj.put("frms_payback_decrease_rate", (16365373-40128981)/16365373*100);// 本月回款下降比例
        obj.put("frms_lastY_amount_decrease_rate", 0);// 没有上年度数据，默认通过
        obj.put("frms_lastM_decrease_rate", ((38123870L-35717483L)/38123870L * 100L));
//        obj.put("frms_lastM_decrease_rate", BigDecimalUtils.mul(38123870.00-35717483.00));
        obj.put("frms_payback_amount", 4012);// 本月回款金额
        obj.put("frms_lastS_amount_decrease_rate", 0);// 本季度经销商累计订单金额较上年度同期下降比例
        // 经营风险B
        obj.put("frms_last3M_avg_payback_amount", (40128981+23238657+29650180+23238657+10751339+14869115)/3/10000);// 最近三个月公司、法人账户月平均销售回款入金额度
        obj.put("frms_3M_payback_amount", (40128981+23238657+29650180+23238657+10751339+14869115)/10000); // 连续出现3个月出现公司、法人账户累计销售回款入金额度
        if((36464657-39760351)/36464657*100 >20 && (39760351-38123870)/39760351*100 > 20 && (38123870-35717483)/38123870*100 > 20){
            obj.put("frms_3M_amount_decrease_rate", 20);// 连续3个月出现经销商月度订单金额较上月下降
        }else {
            obj.put("frms_3M_amount_decrease_rate", 0);
        }
        if((35717483+38123870+39760351)/10000/3 < 400){
            obj.put("frms_last3M_avg_amount", 0);// 近3个月经销商月平均订单金额
        }else{
            obj.put("frms_last3M_avg_amount", (35717483+38123870+39760351)/10000/3);
        }
        // 财务风险A
        obj.put("frms_net_asset_month_decrease_rate", 25);//
        obj.put("frms_company_debt_rate", 108585492L/331237819*100);// 公司资产负债率
        obj.put("frms_company_gross_margin", 117742045/732071329*100);// 公司毛利润率
        obj.put("frms_company_net_profit_rate", 57653541/732071329*100);// 净利润率
        obj.put("frms_receivables_turnover_days", (44920879+31881817)/2*360/732071329); // 公司应收账款周转天数
        obj.put("frms_stock_turnover_days", (188718629+144589746)/2*360/613794926);// 存货周转天数
        obj.put("frms_main_business_amount_mount_decrease_rate", 0); // 本月公司主营业务收入金额较上月下降比例(本月58425528，上月无)

        obj.put("frms_last_month_result_a", 1);// 上月通过
        // 财务风险B
        obj.put("frms_net_asset_year_decrease_rate", (455147973-331237819)/455147973*100);// 公司年末净资产余额较年初下降比例
//        obj.put("frms_company_debt_rate", 80);// 公司资产负债率
        obj.put("frms_quick_rate", (303421933-144589746)/108585492 * 100);// 公司速动比率
        obj.put("frms_liquidity_rate", 303421933/108585492*100);// 流动比率
        obj.put("frms_main_business_amount_season_decrease_rate", 0); // 本季度公司主营业务收入金额较上年同期下降比例(上年度没有)
        obj.put("frms_season_net_profit_amount", 57653541-63973509); // 本季度公司累计净利润金额
        obj.put("frms_is_loss", 1); // 经我司评估本年度公司净利润将处于亏损状态

        obj.put("frms_last_month_result_b", 1);// 上月通过
        // 法律信用风险
        obj.put("frms_lawsuit_net_asset_rate", 40); // 诉讼金额比例
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
            ruleArray.forEach(a -> {
                JSONArray risks = JSON.parseObject(a.toString()).getJSONObject("result").getJSONArray("risks");
                String taskName = JSON.parseObject(a.toString()).getString("taskName");
                risks.forEach(risk -> {
                    JSONObject r = JSON.parseObject(risk.toString());
                    String tmp = r.getString("ruleName");
                    JSONObject rule = new JSONObject();
                    rule.put("taskName", taskName);
                    rule.put("ruleCode", tmp.substring(tmp.indexOf(":") + 1, tmp.lastIndexOf(":")));
                    rule.put("ruleName", tmp.substring(tmp.lastIndexOf(":") + 1, tmp.length()));

                    rules.add(rule);
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
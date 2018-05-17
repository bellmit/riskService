package com.bigdata;

import com.alibaba.fastjson.JSONObject;
import com.bigdata.util.BigDecimalUtils;
import com.bigdata.util.HttpConnectionUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Desciption
 * Create By  li.bo
 * CreateTime 2018/2/6 13:41
 * UpdateTime 2018/2/6 13:41
 */
@Slf4j
public class MyTest {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Test
    public void test14234() {
        double a = BigDecimalUtils.mul(BigDecimalUtils.div(BigDecimalUtils.sub(29419948L, 3911355L), 29419948L, 2), 100);
        log.info(""+a);
    }

    @Test
    public void testRisk() {

        JSONObject obj = new JSONObject();
        obj.put("frms_flow_id", "47dd2f9d-60a5-41a3-ab5a-205f5742ddbc");
        obj.put("frms_uuid", "51c96ceb-3fed-4f9e-b0cd-b16444aca2b0");
        obj.put("frms_customer_id", "homefax");
        obj.put("frms_user_id", "xd");
        obj.put("frms_biz_code", "PAY.CREDIT");
        obj.put("frms_ysx_limit", 900000);
        obj.put("frms_sp_hit_cnt", 1);

        String result = HttpConnectionUtils.setConnection("http://172.16.102.15:9181/flow/syncAudit?processKey=flow17", obj);
        JSONObject resultObj = JSONObject.parseObject(result);
        log.info("返回结果： " + resultObj.toString());
    }

    @Test
    public void testCreditRisk() {

        JSONObject obj = new JSONObject();
        obj.put("frms_flow_id", "47dd2f9d-60a5-41a3-ab5a-205f5742ddbc");
        obj.put("frms_uuid", "51c96ceb-3fed-4f9e-b0cd-b16444aca2b0");
        obj.put("frms_customer_id", "homefax");
        obj.put("frms_user_id", "xd");
        obj.put("frms_biz_code", "PAY.CREDIT");

        obj.put("frms_sc_lst12_rent_per", 95);// 商场出租率
        obj.put("frms_sc_lst12_capture_per", 98);// 商场收缴率
        obj.put("frms_sc_province", "北京");// 商场所在城市属于省份
        obj.put("frms_sc_type", "自营");// 商场性质
        obj.put("frms_xd_sc_amt", 100000000);// 1-10月经营收入（万）

        obj.put("frms_sc_name", "上海金桥商场");// 商场

        obj.put("frms_xd_outer_blacklist", "0");// 外部黑名单接口
        obj.put("frms_xd_customer", "1");// 申请人为已有借款客户
        obj.put("frms_xd_cus_payoff", "0");// 是否尚有贷款未还清
        obj.put("frms_xd_cus_loan_status", "0");// 当前状态是否逾期
        obj.put("frms_xd_max_overdue_day", 6);// 逾期天数
        obj.put("frms_xd_high_risk_cus", "0");// 高风险的客户
        obj.put("frms_is_guaranteed", "0");// 是否做担保

        obj.put("frms_xd_max_buz_term", 36);// 最长店铺经营年限
        obj.put("frms_xd_booth_num", 1);// 展位数量
        obj.put("frms_xd_booth_level", 1);// 展位等级
        obj.put("frms_xd_default_rent_num", 0);// 店铺在近12月租金拖欠次数
        obj.put("frms_xd_12m_avg_amt", 10);// 店铺最近12个月平均月销售金额
        obj.put("frms_xd_12m_0_amt", 3);// 申请人店铺过去12个月统一收银为0的月份数
        obj.put("frms_xd_apply_limit", 20);// 申请额度
        obj.put("frms_xd_used_limit", 10);// 已有贷款额度
        obj.put("frms_xd_pre_credit_limit", 40);// 预授信额度

        obj.put("frms_sc_buz_property", "自营");
        obj.put("frms_sc_city_grade", "一级城市");
        obj.put("frms_sc_rent_per", 98);
        obj.put("frms_sh_pp_grade", "进口");
        obj.put("frms_cuz_control_level", "战略vip合作伙伴");
        obj.put("frms_xd_12m_hcomplain_num", 0);
        obj.put("frms_xd_12m_acitity_amt", "35000");
        obj.put("frms_xd_6m_rent_rank", 20);
        obj.put("frms_xd_90d_amt_rank", 9);
        obj.put("frms_xd_180d_sale_amt", 50);
        obj.put("frms_xd_180d_rent_amt", 5);
        obj.put("frms_xd_6m_sale_amt", 1);
        obj.put("frms_xd_12m_sale_amt", 2);
        obj.put("frms_xd_18m_sale_amt", 3);

        obj.put("frms_xd_rent_everymonth", 6);
        obj.put("frms_xd_pp_type", "1");
        obj.put("frms_xd_buz_category", "地板");
        obj.put("frms_xd_pp_grade", "待定级");
        obj.put("frms_xd_12m_mcomplain_num", 1);

        obj.put("frms_xd_min_overdue_day", 2);
        obj.put("frms_xd_record", "1");

        obj.put("frms_sc_limit_ratio", 2);
        obj.put("frms_belong_booth_rent", 10);
        obj.put("frms_control_booth_rent", 1);
        obj.put("frms_reality_booth_rent", 2);
        obj.put("frms_no_list_rent", 1);
        obj.put("frms_other_manage_rent", 1);
        obj.put("frms_12m_avg_sale", 15);

//        obj.put("frms_ysx_grade", "AAA-b");

        String result = HttpConnectionUtils.setConnection("http://172.16.102.15:9181/flow/syncAudit?processKey=flow21", obj);
        JSONObject resultObj = JSONObject.parseObject(result);
        log.info("返回结果： " + resultObj.toString());
    }

    @Test
    public void testPatrick() {

        JSONObject obj = new JSONObject();
        obj.put("frms_flow_id", "47dd2f9d-60a5-41a3-ab5a-205f5742ddbc");
        obj.put("frms_uuid", "51c96ceb-3fed-4f9e-b0cd-b16444aca2b0");
        obj.put("frms_customer_id", "homefax");
        obj.put("frms_user_id", "xd");
        obj.put("frms_biz_code", "PAY.CREDIT");

        obj.put("frms_limit_rules_cnt", 1);
        obj.put("frms_prohibitive_rules_cnt", 0);
        obj.put("frms_suggestive_rules_cnt", 2);
        obj.put("frms_ysx_score", 772);


        String result = HttpConnectionUtils.setConnection("http://172.16.102.15:9181/flow/syncAudit?processKey=flow22", obj);
        JSONObject resultObj = JSONObject.parseObject(result);
        log.info("返回结果： " + resultObj.toString());
    }

    @Test
    public void testSP() throws ParseException {

        JSONObject obj = new JSONObject();
        obj.put("frms_flow_id", "47dd2f9d-60a5-41a3-ab5a-205f5742ddbc");
        obj.put("frms_uuid", "51c96ceb-3fed-4f9e-b0cd-b16444aca2b0");
        obj.put("frms_customer_id", "homefax");
        obj.put("frms_user_id", "xd");
        obj.put("frms_biz_code", "PAY.CREDIT");

        obj.put("frms_loan_limit", 10);
        obj.put("frms_loan_deadline", 1);
        obj.put("frms_apply_age", 30);
        obj.put("frms_high_risk_area", "上海");
        obj.put("frms_loan_lawsuit_record", 0);
        obj.put("frms_other_lawsuit_record", 0);
        obj.put("frms_if_have_local_house", 1);
        obj.put("frms_apply_marital_status", "已婚");
        obj.put("frms_if_nonlocal", 0);

        obj.put("frms_12m_overdue_cnt", 2);// 最近12个月贷款、贷记卡的大逾期期数
        obj.put("frms_24m_overdue_cnt", 3);// 最近24个月贷款、贷记卡的最大逾期期数
        obj.put("frms_if_special_risk", 0);// 有无特殊风险情况
        obj.put("frms_6m_m2_overdue_cnt", 2);// 贷款、贷记卡最近6个月M2+逾期次数
        obj.put("frms_12m_m2_overdue_cnt", 1);// 贷款、贷记卡最近12个月M2+逾期次数
        obj.put("frms_12m_m1_overdue_cnt", 3);// 贷款、贷记卡最近12个月M1+逾期次数
        obj.put("frms_24m_m2_overdue_cnt", 1);// 贷款、贷记卡最近24个月M2+逾期次数
        obj.put("frms_24m_m1_overdue_cnt", 5);// 贷款、贷记卡最近24个月M1+逾期次数
        obj.put("frms_6m_m1_overdue_cnt", 1);// 贷款、贷记卡最近6个月M1+逾期次数
        obj.put("frms_last_overdue_months", 7);// 最近一次逾期距离当前月份数
        obj.put("frms_debit_use_rate", 0.8);// 当前信用卡当前额度使用率
        obj.put("frms_bad_guarantee_record", 0);// 风险分类为不良的对外担保记录
        obj.put("frms_if_zx_record", 1);// 是否有征信历史记录
        obj.put("frms_overdue_loan_account_cnt", 0);// 当前状态为逾期的贷款账户数
        obj.put("frms_overdue_credit_account_cnt", 0);// 当前状态为逾期的贷记卡账户数
        obj.put("frms_overdue_xcredit_account_cnt", 1);// 当前状态为逾期的准贷记卡账户数
        obj.put("frms_loan_settle_cert", 0);// 有相应贷款结清证明
        obj.put("frms_1m_loan_search_cnt", 3);// 最近1个月贷款审批或信用卡审批征信查询次数
        obj.put("frms_3m_loan_search_cnt", 6);// 最近3个月贷款审批或信用卡审批征信查询次数
        obj.put("frms_last3m_search_cnt", 2);// 最近3个月本人查询记录次数
        obj.put("frms_all_debt", 7);// 申请人与配偶的总负债
        obj.put("frms_all_property", 10);// 所有房产价值

        obj.put("frms_spouse_12m_overdue_cnt", 2);// 最近12个月贷款、贷记卡的大逾期期数
        obj.put("frms_spouse_24m_overdue_cnt", 3);// 最近24个月贷款、贷记卡的最大逾期期数
        obj.put("frms_spouse_if_special_risk", 0);// 有无特殊风险情况
        obj.put("frms_spouse_6m_m2_overdue_cnt", 2);// 贷款、贷记卡最近6个月M2+逾期次数
        obj.put("frms_spouse_12m_m2_overdue_cnt", 1);// 贷款、贷记卡最近12个月M2+逾期次数
        obj.put("frms_spouse_12m_m1_overdue_cnt", 3);// 贷款、贷记卡最近12个月M1+逾期次数
        obj.put("frms_spouse_24m_m2_overdue_cnt", 1);// 贷款、贷记卡最近24个月M2+逾期次数
        obj.put("frms_spouse_24m_m1_overdue_cnt", 5);// 贷款、贷记卡最近24个月M1+逾期次数
        obj.put("frms_spouse_6m_m1_overdue_cnt", 1);// 贷款、贷记卡最近6个月M1+逾期次数
        obj.put("frms_spouse_last_overdue_months", 7);// 最近一次逾期距离当前月份数
        obj.put("frms_spouse_debit_use_rate", 0.8);// 当前信用卡当前额度使用率
        obj.put("frms_spouse_bad_guarantee_record", 0);// 风险分类为不良的对外担保记录
        obj.put("frms_spouse_if_zx_record", 1);// 是否有征信历史记录
        obj.put("frms_spouse_overdue_loan_account_cnt", 0);// 当前状态为逾期的贷款账户数
        obj.put("frms_spouse_overdue_credit_account_cnt", 0);// 当前状态为逾期的贷记卡账户数
        obj.put("frms_spouse_overdue_xcredit_account_cnt", 1);// 当前状态为逾期的准贷记卡账户数
        obj.put("frms_spouse_loan_settle_cert", 0);// 有相应贷款结清证明
        obj.put("frms_spouse_1m_loan_search_cnt", 3);// 最近1个月贷款审批或信用卡审批征信查询次数
        obj.put("frms_spouse_3m_loan_search_cnt", 6);// 最近3个月贷款审批或信用卡审批征信查询次数
        obj.put("frms_spouse_last3m_search_cnt", 2);// 最近3个月本人查询记录次数
        obj.put("frms_belong_booth_rent", 100000);
        obj.put("frms_control_booth_rent", 20000);
        obj.put("frms_reality_booth_rent", 40000);
        obj.put("frms_sale_rent_ratio", 20000);
        obj.put("frms_avg_used_limit", 200000);
        obj.put("frms_avg_repay_limit", 10000);
        obj.put("frms_xd_booth_num", 6);

        obj.put("frms_ysx_grade", "AAA-b");
        obj.put("frms_ysx_limit", 150000);

        obj.put("frms_booth_deadline", 1533686888000L);
        obj.put("frms_wholesale_repayment_day", 1528992000000L);

        String result = HttpConnectionUtils.setConnection("http://172.16.102.15:9181/flow/syncAudit?processKey=flow23", obj);
        JSONObject resultObj = JSONObject.parseObject(result);
        log.info("返回结果： " + resultObj.toString());
    }

    @Test
    public void testSanWei() {

        JSONObject obj = new JSONObject();
        obj.put("frms_flow_id", "47dd2f9d-60a5-41a3-ab5a-205f5742ddbc");
        obj.put("frms_uuid", "51c96ceb-3fed-4f9e-b0cd-b16444aca2b0");
        obj.put("frms_customer_id", "homefax");
        obj.put("frms_user_id", "xd");
        obj.put("frms_biz_code", "PAY.CREDIT");

        obj.put("frms_material_fit", "是");// 审核初审审核内容与上传资料是否一致
        obj.put("frms_contact_fit", "是");// 与申请人本人、填写的联系人、客户经理电话沟通
        obj.put("frms_default_rent", "是");// 申请人店铺在近12月租金拖欠次数
        obj.put("frms_booth_change", "否");// 贷款申请时客户计划被商场调整或客户不计划续租
        obj.put("frms_cc_apply", "否");// 贷款申请时客户已经提出提出撤场申请
        obj.put("frms_related_actual_controller", "否");// 申请人与实际控制人是否关联
        obj.put("frms_bad_cooperate", "否");// 优良中差四选项,选项为差则禁止
        obj.put("frms_high_risk_buz", "是");// 与申请人及商场客户经理沟通，申请人已投资高风险行业
        obj.put("frms_whole_live_addr", "否");// 与申请人及联系人沟通，确定居住地址完整性
        obj.put("frms_credit_manager_suggest", "驳回");// 综合系统审批额度及商场、商户沟通情况批复建议、注明批复逻辑
        obj.put("frms_comprehensive_quality", "差");//  借款人的综合素质评价

        String result = HttpConnectionUtils.setConnection("http://172.16.102.15:9181/flow/syncAudit?processKey=flow24", obj);
        JSONObject resultObj = JSONObject.parseObject(result);
        log.info("返回结果： " + resultObj.toString());
    }

    @Test
    public void testL() {
        Long a = Long.parseLong("4");
        log.info(a.toString());
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        String tmp = String.valueOf(-2.0);
        calendar.add(Calendar.MONTH, Integer.parseInt(tmp.substring(0, tmp.length()-2)));
        log.info(calendar.getTime().toString());
    }
}

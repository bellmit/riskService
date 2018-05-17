package com.bigdata;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Desciption
 * Create By  li.bo
 * CreateTime 2018/4/18 9:51
 * UpdateTime 2018/4/18 9:51
 */
@Slf4j
public class CreditTest {

    RestTemplate template;
    String url = "http://172.16.102.15:9181/flow/syncAudit?processKey=flow23";

    @Before
    public void Before() {
        template = TestUtils.initTemplate();
    }

    @Test
    public void test01() {
        Map<String, Object> map = Maps.newHashMap();

        map.put("frms_flow_id", "47dd2f9d-60a5-41a3-ab5a-205f5742ddbc");
        map.put("frms_uuid", "51c96ceb-3fed-4f9e-b0cd-b16444aca2b0");
        map.put("frms_customer_id", "homefax");
        map.put("frms_user_id", "xd");
        map.put("frms_biz_code", "PAY.CREDIT");

        map.put("frms_loan_limit", 10);
        map.put("frms_loan_deadline", 1);
        map.put("frms_apply_age", 30);
        map.put("frms_high_risk_area", "上海");
        map.put("frms_loan_lawsuit_record", 0);
        map.put("frms_other_lawsuit_record", 0);
        map.put("frms_if_have_local_house", 1);
        map.put("frms_apply_marital_status", "已婚");
        map.put("frms_if_nonlocal", 0);

        map.put("frms_12m_overdue_cnt", 2);// 最近12个月贷款、贷记卡的大逾期期数
        map.put("frms_24m_overdue_cnt", 3);// 最近24个月贷款、贷记卡的最大逾期期数
        map.put("frms_if_special_risk", 0);// 有无特殊风险情况
        map.put("frms_6m_m2_overdue_cnt", 2);// 贷款、贷记卡最近6个月M2+逾期次数
        map.put("frms_12m_m2_overdue_cnt", 1);// 贷款、贷记卡最近12个月M2+逾期次数
        map.put("frms_12m_m1_overdue_cnt", 3);// 贷款、贷记卡最近12个月M1+逾期次数
        map.put("frms_24m_m2_overdue_cnt", 1);// 贷款、贷记卡最近24个月M2+逾期次数
        map.put("frms_24m_m1_overdue_cnt", 5);// 贷款、贷记卡最近24个月M1+逾期次数
        map.put("frms_6m_m1_overdue_cnt", 1);// 贷款、贷记卡最近6个月M1+逾期次数
        map.put("frms_last_overdue_months", 7);// 最近一次逾期距离当前月份数
        map.put("frms_debit_use_rate", 0.8);// 当前信用卡当前额度使用率
        map.put("frms_bad_guarantee_record", 0);// 风险分类为不良的对外担保记录
        map.put("frms_if_zx_record", 1);// 是否有征信历史记录
        map.put("frms_overdue_loan_account_cnt", 0);// 当前状态为逾期的贷款账户数
        map.put("frms_overdue_credit_account_cnt", 0);// 当前状态为逾期的贷记卡账户数
        map.put("frms_overdue_xcredit_account_cnt", 1);// 当前状态为逾期的准贷记卡账户数
        map.put("frms_loan_settle_cert", 0);// 有相应贷款结清证明
        map.put("frms_1m_loan_search_cnt", 3);// 最近1个月贷款审批或信用卡审批征信查询次数
        map.put("frms_3m_loan_search_cnt", 6);// 最近3个月贷款审批或信用卡审批征信查询次数
        map.put("frms_last3m_search_cnt", 2);// 最近3个月本人查询记录次数
        map.put("frms_all_debt", 7);// 申请人与配偶的总负债
        map.put("frms_all_property", 10);// 所有房产价值

        map.put("frms_spouse_12m_overdue_cnt", 2);// 最近12个月贷款、贷记卡的大逾期期数
        map.put("frms_spouse_24m_overdue_cnt", 3);// 最近24个月贷款、贷记卡的最大逾期期数
        map.put("frms_spouse_if_special_risk", 0);// 有无特殊风险情况
        map.put("frms_spouse_6m_m2_overdue_cnt", 2);// 贷款、贷记卡最近6个月M2+逾期次数
        map.put("frms_spouse_12m_m2_overdue_cnt", 1);// 贷款、贷记卡最近12个月M2+逾期次数
        map.put("frms_spouse_12m_m1_overdue_cnt", 3);// 贷款、贷记卡最近12个月M1+逾期次数
        map.put("frms_spouse_24m_m2_overdue_cnt", 1);// 贷款、贷记卡最近24个月M2+逾期次数
        map.put("frms_spouse_24m_m1_overdue_cnt", 5);// 贷款、贷记卡最近24个月M1+逾期次数
        map.put("frms_spouse_6m_m1_overdue_cnt", 1);// 贷款、贷记卡最近6个月M1+逾期次数
        map.put("frms_spouse_last_overdue_months", 7);// 最近一次逾期距离当前月份数
        map.put("frms_spouse_debit_use_rate", 0.8);// 当前信用卡当前额度使用率
        map.put("frms_spouse_bad_guarantee_record", 0);// 风险分类为不良的对外担保记录
        map.put("frms_spouse_if_zx_record", 1);// 是否有征信历史记录
        map.put("frms_spouse_overdue_loan_account_cnt", 0);// 当前状态为逾期的贷款账户数
        map.put("frms_spouse_overdue_credit_account_cnt", 0);// 当前状态为逾期的贷记卡账户数
        map.put("frms_spouse_overdue_xcredit_account_cnt", 1);// 当前状态为逾期的准贷记卡账户数
        map.put("frms_spouse_loan_settle_cert", 0);// 有相应贷款结清证明
        map.put("frms_spouse_1m_loan_search_cnt", 3);// 最近1个月贷款审批或信用卡审批征信查询次数
        map.put("frms_spouse_3m_loan_search_cnt", 6);// 最近3个月贷款审批或信用卡审批征信查询次数
        map.put("frms_spouse_last3m_search_cnt", 2);// 最近3个月本人查询记录次数
        map.put("frms_belong_booth_rent", 100000);
        map.put("frms_control_booth_rent", 20000);
        map.put("frms_reality_booth_rent", 40000);
        map.put("frms_sale_rent_ratio", 20000);
        map.put("frms_avg_used_limit", 200000);
        map.put("frms_avg_repay_limit", 10000);
        map.put("frms_xd_booth_num", 6);

        map.put("frms_ysx_grade", "AAA-b");
        map.put("frms_ysx_limit", 150000);

        map.put("frms_booth_deadline", "1536336000000");
        map.put("frms_wholesale_repayment_day", "1539792000000");

        Map<?, ?> result = template.postForObject(url, map, Map.class);
        log.info(JSON.toJSONString(result));
    }
}

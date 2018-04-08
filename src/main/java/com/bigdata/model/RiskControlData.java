package com.bigdata.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.UUID;

/**
 * Desciption 风控请求探头数据
 * Create By  li.bo
 * CreateTime 2018/3/12 14:13
 * UpdateTime 2018/3/12 14:13
 */
@NoArgsConstructor
@Accessors(chain = true)
@Setter
@Getter
@ToString
public class RiskControlData {

    // 必填基本信息
    private String frms_flow_id;
    private String frms_customer_id;
    private String frms_user_id;
    private String frms_biz_code;

    // 经营风险A
    private double frms_payback_decrease_rate;                      // 本月回款下降比例
    private double frms_lastY_amount_decrease_rate;                 // 本月经销商订单金额较上年度同期下降比例
    private double frms_lastM_decrease_rate;                        // 本月经销商订单金额较上月下降比例
    private double frms_payback_amount;                             // 本月回款金额
    private double frms_lastS_amount_decrease_rate;                 // 本季度经销商累计订单金额较上年度同期下降比例
    private double frms_refund_amount_rate;                         // 本月经销商订单退货金额占当月订单总额的比率（订单退货率）
    // 经营风险B
    private double frms_last3M_avg_payback_amount;                  // 最近三个月公司、法人账户月平均销售回款入金额度
    private double frms_3M_payback_amount;                          // 连续出现3个月出现公司、法人账户累计销售回款入金额度
    private double frms_3M_amount_decrease_rate;                    // 连续3个月出现经销商月度订单金额较上月下降
    private double frms_last3M_avg_amount;                          // 近3个月经销商月平均订单金额
    private double frms_last3M_refund_increase_rate;                // 连续3个月月出现经销商订单退货率较上月增长比率
    // 财务风险A
    private double frms_net_asset_month_decrease_rate;                 // 公司净资产较上月下降比例
    private double frms_company_debt_rate;                          // 公司资产负债率
    private double frms_company_gross_margin;                       // 公司毛利润率
    private double frms_company_net_profit_rate;                    // 净利润率
    private double frms_receivables_turnover_days;                  // 公司应收账款周转天数
    private double frms_stock_turnover_days;                        // 存货周转天数
    private double frms_main_business_amount_mount_decrease_rate;   // 本月公司主营业务收入金额较上月下降比例
    private int frms_last_month_result_a;                           // 上月通过
    // 财务风险B
    private double frms_net_asset_year_decrease_rate;               // 公司年末净资产余额较年初下降比例
    private double frms_quick_rate;                                 // 公司速动比率
    private double frms_liquidity_rate;                             // 流动比率
    private double frms_main_business_amount_season_decrease_rate;  // 本季度公司主营业务收入金额较上年同期下降比例
    private double frms_season_net_profit_amount;                   // 本季度公司累计净利润金额
    private int frms_is_loss;                                       // 经我司评估本年度公司净利润将处于亏损状态
    private int frms_last_month_result_b;                           // 上月通过
    // 法律信用风险
    private double frms_lawsuit_net_asset_rate;                     // 诉讼金额比例
    private int frms_is_overdue;                                    // 借款人、借款企业贷款尚处于逾期状态
    // 其他风险
    private int frms_is_reject;

}

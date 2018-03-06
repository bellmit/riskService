package com.bigdata.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Desciption 个人对私银行流水
 * Create By  li.bo
 * CreateTime 2018/3/6 15:53
 * UpdateTime 2018/3/6 15:53
 */
@NoArgsConstructor
@Accessors(chain = true)
@Setter
@Getter
@ToString
public class PrivateBankStatement {

    private Long id;
    private String userId;                  // 用户号
    private String accountNo;               // 本方账号
    private Date dealTime;                  // 交易时间
    private String summary;                 // 摘要
    private String channel;                 // 交易场所
    private String dealCountryName;         // 交易国家或地区简称
    private String cashExCode;              // 钞汇标识
    private BigDecimal dealInAmount;        // 交易金额(收入)
    private BigDecimal dealOutAmount;       // 交易金额(支出)
    private String dealCurrency;            // 交易币种
    private BigDecimal billingInAmount;     // 记账金额(收入)
    private BigDecimal billingOutAmount;    // 记账金额(支出)
    private String billingCurrency;         // 记账币种
    private BigDecimal balance;             // 余额
    private String recipientAccountName;    // 对方户名
}

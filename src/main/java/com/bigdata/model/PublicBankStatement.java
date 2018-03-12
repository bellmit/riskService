package com.bigdata.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Desciption 工厂对公银行流水
 * Create By  li.bo
 * CreateTime 2018/3/6 9:48
 * UpdateTime 2018/3/6 9:48
 */
@NoArgsConstructor
@Accessors(chain = true)
@Setter
@Getter
@ToString
public class PublicBankStatement {

    private Long id;                    // id
    private String factoryId;           // 工厂号
    private String voucherNo;           // 凭证号
    private String accountNo;           // 本方账号
    private String recipientAccountNo;  // 对方账号
    private Date dealTime;              // 交易时间
    private String type;                // 类型：借/贷
    private BigDecimal debitAmount;     // 借方发生额
    private BigDecimal creditAmount;    // 贷方发生额
    private String recipientBankNo;     // 对方行号
    private String summary;             // 摘要
    private String use;                 // 用途
    private String recipientCompanyName;// 对方单位名称
    private BigDecimal balance;         // 余额
    private String description;         // 附言(个性化信息)
    private String channel;             // 渠道(保留字段)
}

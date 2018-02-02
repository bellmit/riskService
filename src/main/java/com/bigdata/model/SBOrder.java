package com.bigdata.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * Desciption 深一号订单
 * Create By  li.bo
 * CreateTime 2018/1/31 10:53
 * UpdateTime 2018/1/31 10:53
 */
@Accessors(chain = true)
@NoArgsConstructor
@Setter
@Getter
@ToString
public class SBOrder {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String sjly;    // 数据来源（松堡王国|松堡家俬）
    private String shbz;    // 审核标志
    private String gbbz;    // 关闭标志
    private Date rq;        // 日期
    private String rqString;
    private String ghdw;    // 购货单位
    private String ghfs;    // 销售方式
    private String jhfs;    // 交货方式
    private String djbh;    // 单据编号
    private String bz;      // 币别
    private String jhdd;    // 交货地点
    private String bm;      // 部门
    private String ywy;     // 业务员
    private String zd;      // 制单
    private String jsfs;    // 结算方式
    private String cpmc;    // 产品名称
    private String ggbh;    // 规格型号
    private String dw;      // 单位
    private String sl;      // 数量
    private String je;      // 金额
    private String shr;     // 审核人
    private String jhrq;    // 交货日期
    private String cpcdm;   // 产品长代码
    private String hywgbbz; // 行业务关闭标志
    private String jyjhrq;  // 建议交货日期
    private String dhjg;    // 订货机构
    private String htdh;    // 合同单号
    private String zy;      // 摘要
    private String shrq;    // 审核日期
    private String hsdj;    // 含税单价
    private String xxse;    // 销项税额
    private String jshj;    // 价税合计
    private String zdjg;    // 制单机构
    private String jhlx;    // 计划类别
    private String wlfs;    // 物流方式
    private String wlfs1;   // 物流方式1
    private String sksl;    // 锁库数量
    private String ksksl;   // 可锁库数量
    private String jbdwsksl;// 基本单位锁库数量
}

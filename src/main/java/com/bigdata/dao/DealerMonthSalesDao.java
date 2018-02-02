package com.bigdata.dao;


import com.bigdata.model.DealerMonthSales;

import java.util.List;

/**
 * Desciption 经销商每月销售总金额
 * Create By  li.bo
 * CreateTime 2017/12/24 15:18
 * UpdateTime 2017/12/24 15:18
 */
public interface DealerMonthSalesDao {

    List<DealerMonthSales> findAmountByYearAndMonth() throws Exception;

    List<DealerMonthSales> findAllYears() throws Exception;
}

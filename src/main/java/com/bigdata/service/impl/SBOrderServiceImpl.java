package com.bigdata.service.impl;

import com.bigdata.dao.SBOrderDao;
import com.bigdata.model.SBOrder;
import com.bigdata.service.SBOrderService;
import com.bigdata.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * Desciption
 * Create By  li.bo
 * CreateTime 2018/1/31 13:50
 * UpdateTime 2018/1/31 13:50
 */
@Service("sbOrderService")
public class SBOrderServiceImpl implements SBOrderService {

    @Autowired
    private SBOrderDao sbOrderDao;

    @Override
    public List<SBOrder> listOrders(Map<String, Object> map) {

        map.put("page", PageUtil.calculateOffset(Integer.parseInt(map.get("page").toString()), Integer.parseInt(map.get("perPage").toString())));

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        List<SBOrder> orders = sbOrderDao.getList(map);
        orders.forEach(order -> {
            String date = formatter.format(order.getRq());
            order.setRqString(date);
        });

        return orders;
    }

    @Override
    public Integer countOrders(Map<String, Object> map) {

        return sbOrderDao.getCount(map);
    }

    @Override
    public Long getOrderAmount(Map<String, Object> map) {

        return sbOrderDao.getOrderAmount(map);
    }
}

package com.bigdata.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bigdata.service.DealerService;
import com.bigdata.util.Enum.ResponseResultEnum;
import com.bigdata.util.ResultBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Desciption 经销商
 * Create By  li.bo
 * CreateTime 2017/10/23 15:21
 * UpdateTime 2017/10/23 15:21
 */
@RestController
public class DealerController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    DealerService dealerService;

    /**
     * 地图-经销商全国分布
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/dealer", method = RequestMethod.GET)
    public ResultBody<? extends Object> getDetail(HttpServletRequest request, HttpServletResponse response) {

        JSONObject result = dealerService.getAllDealer();
        logger.info(result.toString());

        return new ResultBody(ResponseResultEnum.SUCCESS.getFeatureType(), ResponseResultEnum.SUCCESS.getDescription(), result);
    }

    /**
     * 柱状图-经销商省份分布列表
     *
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/dealer/province")
    public ResultBody<? extends Object> getDetailByProvince(HttpServletRequest request, HttpServletResponse response) {

        JSONObject result = dealerService.getDealerShopByProvince();
        logger.info(result.toString());

        return new ResultBody(ResponseResultEnum.SUCCESS.getFeatureType(), ResponseResultEnum.SUCCESS.getDescription(), result);
    }

    /**
     * 柱状图-经销商城市分布列表
     *
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/dealer/city")
    public ResultBody<? extends Object> getDetailByCity(HttpServletRequest request, HttpServletResponse response) {

        JSONObject result = dealerService.getDealerShopByCity();
        logger.info(result.toString());

        return new ResultBody(ResponseResultEnum.SUCCESS.getFeatureType(), ResponseResultEnum.SUCCESS.getDescription(), result);
    }

    /**
     * 折线图-每月经销商的销售总金额
     *
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/dealer/monthAmount")
    public ResultBody<? extends Object> getAmountByYearAndMonth(HttpServletRequest request, HttpServletResponse response) {

        JSONArray result = dealerService.getAmountByYearAndMonth();
        logger.info(result.toString());

        return new ResultBody(ResponseResultEnum.SUCCESS.getFeatureType(), ResponseResultEnum.SUCCESS.getDescription(), result);
    }
}

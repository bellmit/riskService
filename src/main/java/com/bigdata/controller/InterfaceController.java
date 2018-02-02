package com.bigdata.controller;

import com.alibaba.fastjson.JSONObject;
import com.bigdata.util.Enum.ResponseResultEnum;
import com.bigdata.util.HttpConnectionUtils;
import com.bigdata.util.RequestJsonUtils;
import com.bigdata.util.ResultBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Desciption 第三方接口调用
 * Create By  li.bo
 * CreateTime 2017/10/29 18:14
 * UpdateTime 2017/10/29 18:14
 */
@RestController
public class InterfaceController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    // 生产 UAT-http://tgapiuat.homefax.cn/web/portal
    private final static String API_URL = "http://tgapi.homefax.cn/web/portal";

    @RequestMapping(value = "/interface", method = RequestMethod.POST)
    public ResultBody<? extends Object> getDetail(HttpServletRequest request, HttpServletResponse response) {

        ResultBody<? extends Object> resultBody = null;

        // 获取请求中的入参
        JSONObject obj = null;
        try {
            obj = RequestJsonUtils.getRequestJsonString(request);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResultBody(ResponseResultEnum.PARAM_ERROR.getFeatureType(), ResponseResultEnum.PARAM_ERROR.getDescription(), "");
        }

        // 转发请求到服务端
        String result = HttpConnectionUtils.setConnection(API_URL, obj);
        JSONObject resultObj = JSONObject.parseObject(result);
        logger.info("返回结果： " + resultObj.toString());

        // 校验返回结果
        if (resultObj == null) {
            return new ResultBody(ResponseResultEnum.RETRY.getFeatureType(), ResponseResultEnum.RETRY.getDescription(), "");
        }

        // TODO:众安-客户风险甄别-单独处理

        resultBody = new ResultBody(ResponseResultEnum.SUCCESS.getFeatureType(), ResponseResultEnum.SUCCESS.getDescription(), resultObj.getJSONObject("data"));

        return resultBody;
    }
}
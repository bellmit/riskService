package com.bigdata.controller;

import com.alibaba.fastjson.JSON;
import com.bigdata.service.PageRenderResultService;
import com.bigdata.util.Enum.ResponseResultEnum;
import com.bigdata.util.JsonUtils;
import com.bigdata.util.ResultBody;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Desciption 页面模块数据请求
 * Create By  li.bo
 * CreateTime 2018/1/24 13:24
 * UpdateTime 2018/1/24 13:24
 */
@RestController
public class PageRenderResultController {

    @Autowired
    private PageRenderResultService pageRenderResultService;

    @PostMapping("/page")
    public ResultBody<? extends Object> getInformation(HttpServletRequest request, HttpServletResponse response, @RequestBody String objString) {

        response.setHeader("Access-Control-Allow-Origin", "*");

        Map<String, Object> map = Maps.newHashMap();
        map.put("page", JSON.parseObject(objString).getString("page"));
        map.put("module", JSON.parseObject(objString).getInteger("module"));

        return new ResultBody(
                ResponseResultEnum.SUCCESS.getFeatureType(),
                ResponseResultEnum.SUCCESS.getDescription(),
                pageRenderResultService.getOne(map)
                        .map(a -> JsonUtils.parseJSONObject(a.getResult()))
                        .get()
        );
    }
}

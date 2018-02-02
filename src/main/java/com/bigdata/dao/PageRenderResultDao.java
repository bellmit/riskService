package com.bigdata.dao;


import com.bigdata.model.PageRenderResult;

import java.util.Map;

/**
 * Desciption
 * Create By  li.bo
 * CreateTime 2018/1/24 11:13
 * UpdateTime 2018/1/24 11:13
 */
public interface PageRenderResultDao {

    PageRenderResult getOne(Map<String, Object> map);
}

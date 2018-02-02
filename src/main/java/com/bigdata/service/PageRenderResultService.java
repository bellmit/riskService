package com.bigdata.service;


import com.bigdata.model.PageRenderResult;

import java.util.Map;
import java.util.Optional;

/**
 * Desciption
 * Create By  li.bo
 * CreateTime 2018/1/24 11:21
 * UpdateTime 2018/1/24 11:21
 */
public interface PageRenderResultService {
    Optional<PageRenderResult> getOne(Map<String, Object> map);
}

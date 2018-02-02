package com.bigdata.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * Desciption 前端页面结果集
 * Create By  li.bo
 * CreateTime 2018/1/24 10:35
 * UpdateTime 2018/1/24 10:35
 */
@NoArgsConstructor
@Accessors(chain = true)
@Setter
@Getter
@ToString
public class PageRenderResult {

    private String page;        // 页面
    private String pageName;    // 页面名称
    private Integer module;     // 模块编号
    private String moduleName;  // 模块名称
    private String result;      // 结果json
}

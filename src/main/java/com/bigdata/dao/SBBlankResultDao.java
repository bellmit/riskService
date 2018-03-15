package com.bigdata.dao;

import com.bigdata.model.SBBlankResult;

import java.util.List;

/**
 * Desciption
 * Create By  li.bo
 * CreateTime 2018/3/15 9:35
 * UpdateTime 2018/3/15 9:35
 */
public interface SBBlankResultDao {

    List<SBBlankResult> getAll();

    int saveOne(SBBlankResult sbBlankResult);
}

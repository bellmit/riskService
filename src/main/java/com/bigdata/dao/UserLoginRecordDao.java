package com.bigdata.dao;

import org.apache.ibatis.annotations.Param;

/**
 * Desciption
 * Create By  li.bo
 * CreateTime 2018/3/14 14:52
 * UpdateTime 2018/3/14 14:52
 */
public interface UserLoginRecordDao {

    int saveOne(@Param("userId")Long userId, @Param("ip")String id);
}

package com.bigdata.dao;

import com.bigdata.model.User;

/**
 * Desciption 用户Dao
 * Create By  li.bo
 * CreateTime 2018/3/13 14:08
 * UpdateTime 2018/3/13 14:08
 */
public interface UserDao {

    User getUserByUsername(String username);
}

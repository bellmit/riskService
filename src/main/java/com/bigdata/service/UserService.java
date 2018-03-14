package com.bigdata.service;

import com.bigdata.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Desciption 用户服务-重写UserDetailsService（SpringSecurity中默认配置）
 * Create By  li.bo
 * CreateTime 2018/3/13 14:15
 * UpdateTime 2018/3/13 14:15
 */
public interface UserService extends UserDetailsService {
    User getOne(String username);

    int saveUserLoginRecord(Long userId, String ip);
}

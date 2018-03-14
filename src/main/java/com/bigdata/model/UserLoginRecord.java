package com.bigdata.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * Desciption 用户登录记录
 * Create By  li.bo
 * CreateTime 2018/3/14 14:50
 * UpdateTime 2018/3/14 14:50
 */
@NoArgsConstructor
@Accessors(chain = true)
@Setter
@Getter
@ToString
public class UserLoginRecord {

    private Long id ;       // id
    private Long userId;    // 用户id
    private String ip;      // ip地址
    private Date createTime;// 登录时间
}

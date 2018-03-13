package com.bigdata.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * Desciption 用户信息
 * Create By  li.bo
 * CreateTime 2018/3/13 14:06
 * UpdateTime 2018/3/13 14:06
 */
@NoArgsConstructor
@Accessors(chain = true)
@Setter
@Getter
@ToString
public class User {

    private Long id;
    private String username;
    private String password;
    private String passwordSalt;
}

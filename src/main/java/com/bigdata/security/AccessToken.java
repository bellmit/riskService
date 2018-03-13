package com.bigdata.security;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * Desciption Token
 * Create By  li.bo
 * CreateTime 2018/3/13 9:41
 * UpdateTime 2018/3/13 9:41
 */
@Accessors(chain = true)
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AccessToken {

    String access_token;
    String token_type;
    String refresh_token;
    String expires_in;
    String scope;
}

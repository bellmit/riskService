package com.bigdata.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * Desciption
 * Create By  li.bo
 * CreateTime 2018/3/13 10:08
 * UpdateTime 2018/3/13 10:08
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {

//        http.authorizeRequests()
//                .anyRequest().authenticated()
//                .and().formLogin().loginPage("/login").failureForwardUrl("/login?error").permitAll()
//                .and().logout().permitAll();

        http.csrf().disable().cors().disable()
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/rest/**").permitAll()
                .anyRequest().authenticated();

    }
}

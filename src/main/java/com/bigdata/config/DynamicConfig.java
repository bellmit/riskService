package com.bigdata.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.AbstractEnvironment;

import javax.annotation.PostConstruct;

/**
 * Desciption
 * Create By  li.bo
 * CreateTime 2017/7/25 15:09
 * UpdateTime 2017/7/25 15:09
 */
@Configuration
public class DynamicConfig {

    public static final String DYNAMIC_CONFIG_NAME = "dynamic_config";

    @Autowired
    AbstractEnvironment environment;

    @PostConstruct
    public void init(){
        environment.getPropertySources().addFirst(new DynamicPropertySource(DYNAMIC_CONFIG_NAME));
    }
}

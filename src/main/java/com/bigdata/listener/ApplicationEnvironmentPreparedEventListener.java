package com.bigdata.listener;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

import java.util.Iterator;

/**
 * Desciption 获取到ConfigurableEnvironment后可以对配置信息做操作
 * Create By  li.bo
 * CreateTime 2017/7/22 14:11
 * UpdateTime 2017/7/22 14:11
 */
public class ApplicationEnvironmentPreparedEventListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {

    private Logger logger = LoggerFactory.getLogger(ApplicationEnvironmentPreparedEventListener.class);

    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {

        ConfigurableEnvironment env = event.getEnvironment();
        MutablePropertySources mps = env.getPropertySources();
        if (mps != null) {
            Iterator<PropertySource<?>> iterator = mps.iterator();
            while (iterator.hasNext()) {
                PropertySource<?> ps = iterator.next();
                logger.info("ps.getName:{};ps.getSource:{};ps.getClass:{}", ps.getName(), ps.getSource(), ps.getClass());
            }
//            Stream.of(iterator).forEach(ps -> logger.info("ps.getName:{};ps.getSource:{};ps.getClass:{}", ps.getName(), ps.getSource(), ps.getClass()););
        }

    }
}

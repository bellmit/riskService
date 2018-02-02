package com.bigdata.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;

/**
 * Desciption
 * Create By  li.bo
 * CreateTime 2017/7/22 13:43
 * UpdateTime 2017/7/22 13:43
 */
public class ApplicationStartedEventListener implements ApplicationListener<ApplicationStartedEvent> {

    private Logger logger = LoggerFactory.getLogger(ApplicationStartedEventListener.class);

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {

        SpringApplication app = event.getSpringApplication();
        app.setHeadless(false);
        logger.info("==ApplicationStartedEventListener==");
    }
}

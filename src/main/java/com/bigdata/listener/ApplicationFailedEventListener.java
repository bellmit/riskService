package com.bigdata.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.context.ApplicationListener;

/**
 * Desciption SpringBoot启动异常时执行事件
 * Create By  li.bo
 * CreateTime 2017/7/25 10:11
 * UpdateTime 2017/7/25 10:11
 */
public class ApplicationFailedEventListener implements ApplicationListener<ApplicationFailedEvent> {

    private Logger logger = LoggerFactory.getLogger(ApplicationFailedEventListener.class);

    @Override
    public void onApplicationEvent(ApplicationFailedEvent event) {
        Throwable throwable = event.getException();
        handleThrowable(throwable);
    }

    private void handleThrowable(Throwable throwable) {
        logger.info(throwable.getMessage());
    }
}

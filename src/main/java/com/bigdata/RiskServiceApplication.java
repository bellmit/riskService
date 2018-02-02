package com.bigdata;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.bigdata.*"})// 必须加上，告知基础包扫描路径
@SpringBootApplication
public class RiskServiceApplication extends SpringBootServletInitializer {

    private static final Logger logger = LoggerFactory.getLogger(RiskServiceApplication.class);

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        setRegisterErrorPageFilter(false);
        return builder.sources(RiskServiceApplication.class);
    }

    public static void main(String[] args) {
        logger.debug("系统启动！");
//        SpringApplication app = new SpringApplication(Application.class);
//        app.addListeners(new ApplicationStartedEventListener());
//        app.addListeners(new ApplicationEnvironmentPreparedEventListener());
//        app.addListeners(new ApplicationFailedEventListener());
//        app.addListeners(new ApplicationPreparedEventListener());
//        app.run(args);

        SpringApplication.run(RiskServiceApplication.class, args);

    }
}

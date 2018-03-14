package com.bigdata.monitor;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.StopWatch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Desciption Controller层监控
 * Create By  li.bo
 * CreateTime 2018/3/14 10:44
 * UpdateTime 2018/3/14 10:44
 */
@Slf4j
@Aspect
@Component
public class ControllerMonitor {

    @Pointcut("execution(public * com.bigdata.controller..*(..))")
    public void init() {

    }

    @Around(value = "init()")
    public Object round(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object result = proceedingJoinPoint.proceed();
        stopWatch.stop();

        log.info("{} {} {} {}{}", "Controller Module", proceedingJoinPoint.getSignature().toShortString(), "elapsed time:", stopWatch.getTime() / 1000, "s");
        return result;
    }
}

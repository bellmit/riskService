package com.bigdata.monitor;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.StopWatch;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Desciption Service层监控
 * Create By  li.bo
 * CreateTime 2018/3/14 9:56
 * UpdateTime 2018/3/14 9:56
 */
@Slf4j
@Aspect
@Component
public class ServiceMonitor {

    // aop切面
    @Pointcut("execution(* com.bigdata.service..*(..))")
    public void init() {
    }

    // 执行前
    @Before(value = "init()")
    public void before(JoinPoint joinPoint) {

        log.info("{} {}", "Before:", joinPoint.toShortString());
    }

    // 执行时，发生异常
    @AfterThrowing(value = "init()", throwing = "e")
    public void afterThrow(JoinPoint joinPoint, Throwable e) {

        Object[] args = joinPoint.getArgs();
        Signature signature = joinPoint.getSignature();
        log.error("[" + signature.toShortString() + "]" + Arrays.toString(args) + "[" + e.toString() + "]");
    }

    // 执行后
    @After(value = "init()")
    public void after(JoinPoint joinPoint) {

        log.info("{} {}", "After:", joinPoint.toShortString());
    }

    // 环绕整个执行过程，分析耗时
    @Around(value = "init()")
    public Object round(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object result = proceedingJoinPoint.proceed();
        stopWatch.stop();

        log.info("{} {} {} {}{}", "Service Module", proceedingJoinPoint.getSignature().toShortString(), "elapsed time:", stopWatch.getTime() / 1000, "s");
        return result;
    }
}

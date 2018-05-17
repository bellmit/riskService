package com.bigdata;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Desciption
 * Create By  li.bo
 * CreateTime 2018/4/28 16:45
 * UpdateTime 2018/4/28 16:45
 */
@Slf4j
public class ThreadTest {

    @Test
    public void test1() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            try {
                Thread.sleep(2000);
                log.info("Thread run");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executorService.shutdownNow();
        log.info("Main run");
    }
}

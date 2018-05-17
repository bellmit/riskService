package com.bigdata;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ━━━━━━神兽出没━━━━━━
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　　　　　┃
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　　　　　┃
 * 　　┃　　　┻　　　┃
 * 　　┃　　　　　　　┃
 * 　　┗━┓　　　┏━┛
 * 　　　　┃　　　┃神兽保佑, 永无BUG!
 * 　　　　┃　　　┃Code is far away from bug with the animal protecting
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * ━━━━━━感觉萌萌哒━━━━━━
 *
 * Desciption
 * Create By  li.bo
 * CreateTime 2018/5/2 10:15
 * UpdateTime 2018/5/2 10:15
 */
public class ForkJoinExample extends RecursiveTask<Integer> {
    private final int threhold = 5;
    private int first;
    private int last;

    public ForkJoinExample(int first, int last) {
        this.first = first;
        this.last= last;
    }

    @Override
    protected Integer compute() {
        int result = 0;
        if (last - first <= threhold) {
            for (int i = first; i <= last; i++) {
                result += i;
            }
            return result;
        }
        int middle = first + (last - first) / 2;
        ForkJoinExample leftTask = new ForkJoinExample(first, middle);
        ForkJoinExample rightTask = new ForkJoinExample(middle + 1, last);
        leftTask.fork();
        rightTask.fork();
        result = leftTask.join() + rightTask.join();
        return result;
    }

    /***************************************************/
    private int cnt = 0;

    public void add() {
        // 不同步则多线程同时计算出错
//        synchronized (this){
//            cnt++;
//        }
        cnt++;
    }

    public int getCnt() {
        return cnt;
    }

    /***************************************************/
    private AtomicInteger cnt1 = new AtomicInteger();

    public void add1() {
        cnt1.incrementAndGet();
    }

    public int get1() {
        return cnt1.get();
    }

    /***************************************************/

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinExample example = new ForkJoinExample(1, 10000);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Future result = forkJoinPool.submit(example);
        System.out.println(result.get());

        final int threadSize = 1000;
        final CountDownLatch countDownLatch = new CountDownLatch(threadSize);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < threadSize; i++) {
            executorService.execute(() -> {
//                synchronized (example){
//                    example.add();
//                }
                example.add();
                example.add1();
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        System.out.println(example.getCnt());
        System.out.println(example.get1());

    }
}

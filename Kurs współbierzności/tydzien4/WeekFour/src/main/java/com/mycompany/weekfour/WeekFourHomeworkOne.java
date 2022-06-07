package com.mycompany.weekfour;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class WeekFourHomeworkOne {

    public static void main(String[] args) throws InterruptedException {
        AtomicInteger tascCount = new AtomicInteger(0);

        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                1, 4,
                30L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(10),
                new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                tascCount.incrementAndGet();
                System.out.println("[" + Thread.currentThread().getName() + "] Queue is full for " + tascCount.get() + " time");
            }
        }
        );

        Runnable task = new Runnable() {
            @Override
            public void run() {
                System.out.println("[" + Thread.currentThread().getName() + "] Im working...");
                try {
                    Thread.sleep(200);
                    System.out.println("[" + Thread.currentThread().getName() + "] Done");
                } catch (InterruptedException ex) {
                    throw new IllegalStateException(ex);
                }
            }
        };

        while (tascCount.get() < 10) {
            executor.execute(task);
            System.out.println("W petli tascCount=" + tascCount.get());
        }

        Thread.sleep(1000);
        System.out.println("DONE");

        executor.shutdown();
    }
}

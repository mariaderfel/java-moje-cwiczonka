package com.mycompany.tydzien6;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Week6Homework2 {

    public static void main(String[] args) throws InterruptedException {

        Work work = new Work(5, 5);

    }
}

class Work {

    public Work(int numberOfProducer, int numberOfConsumer) throws InterruptedException {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);

        ExecutorService service = new ThreadPoolExecutor(8, 8,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());

        Runnable producer = new Runnable() {
            @Override
            public void run() {
                Random random = new Random();
                try {
                    for (int i = 0; i < 10; ++i) {
                        int randomMumber = random.nextInt(100);
                        System.out.println("[" + Thread.currentThread().getName() + "] element " + i + "  putting to queue: " + randomMumber);
                        queue.put(randomMumber);
                        Thread.sleep(500);
                    }
                } catch (InterruptedException e) {
                    throw new IllegalStateException(e);
                }
            }
        };

        Runnable consumer = new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 10; ++i) {
                        int number = queue.take();
                        System.out.println("[" + Thread.currentThread().getName() + "] element " + i + " taking from queue: " + number);
                        Thread.sleep(500);
                    }
                } catch (Exception e) {
                    throw new IllegalStateException(e);
                }
            }
        };
        for (int i = 0; i < numberOfConsumer; i++) {
            service.execute(consumer);
        }
        for (int i = 0; i < numberOfProducer; i++) {
            service.execute(producer);
        }

        Thread.sleep(30_000);
        service.shutdown();

    }

}

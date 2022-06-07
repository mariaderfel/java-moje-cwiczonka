package pl.kurswspolbieznosci.tydzien4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ExecutorsLesson {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("[" + Thread.currentThread().getName() + "] Starting...");
        AtomicInteger taskCounter = new AtomicInteger(1);

        ExecutorService executor = new ThreadPoolExecutor(4, 4,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>());

        Runnable task = new Runnable() {
            @Override
            public void run() {
                try {
                    int id = taskCounter.getAndIncrement();
                    System.out.println("[" + Thread.currentThread().getName() + "] Doing hard work... [" + id + "]");
                    Thread.sleep(500);
                    System.out.println("[" + Thread.currentThread().getName() + "] DONE. [" + id + "]");
                } catch (InterruptedException e) {
                    throw new IllegalStateException(e);
                }
            }
        };

        executor.execute(task);
        executor.execute(task);
        executor.execute(task);
        executor.execute(task);
        executor.execute(task);
        executor.execute(task);

        executor.shutdown();
        System.out.println("ExecutorService isShutdown="+ executor.isShutdown() +" isTerminated=" + executor.isTerminated());
        executor.awaitTermination(10, TimeUnit.SECONDS);
        System.out.println("ExecutorService isShutdown="+ executor.isShutdown() +" isTerminated=" + executor.isTerminated());

        System.out.println("[" + Thread.currentThread().getName() + "] DONE");
    }
}

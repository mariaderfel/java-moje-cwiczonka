package pl.kurswspolbieznosci.tydzien5;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class FutureLesson1 {
    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        System.out.println("[" + Thread.currentThread().getName() + "] Starting...");

        Callable<Long> longRunningTask = new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                System.out.println("[" + Thread.currentThread().getName() + "] Starting hard work...");
                Thread.sleep(1500);
                System.out.println("[" + Thread.currentThread().getName() + "] Done, returning result...");
                return 123L;
            }
        };

        ExecutorService es = new ThreadPoolExecutor(4, 4,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>());

        Future<Long> future1 = es.submit(longRunningTask);
        Future<Long> future2 = es.submit(longRunningTask);

        System.out.println("[" + Thread.currentThread().getName() + "] Doing something other...");

        future1.isDone();

        future1.get();

        System.out.println("Future1.value = " + future1.get(500, TimeUnit.MILLISECONDS) + " future2.value = " + future2.get(500, TimeUnit.MILLISECONDS));

        System.out.println("[" + Thread.currentThread().getName() + "] DONE");
    }
}

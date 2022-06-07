package pl.kurswspolbieznosci.tydzien5;

import java.time.LocalDateTime;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CallableLesson2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println(LocalDateTime.now() + ": [" + Thread.currentThread().getName() + "] Starting...");

        Callable<Long> task = new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                System.out.println(LocalDateTime.now() + ": [" + Thread.currentThread().getName() + "] Doing hard work..");
                Thread.sleep(1_000);
                System.out.println(LocalDateTime.now() + ": [" + Thread.currentThread().getName() + "] Finihsing...");
                return Math.round(Math.random() * 1000);
            }
        };

        ExecutorService executor = new ThreadPoolExecutor(3, 3,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>());

        final Future<Long> future = executor.submit(task);

        System.out.println(LocalDateTime.now() + ": [" + Thread.currentThread().getName() + "] Has a result of calculation: " + future.get());

        System.out.println(LocalDateTime.now() + ": [" + Thread.currentThread().getName() + "] Done");
    }
}

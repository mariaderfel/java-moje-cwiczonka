package pl.kurswspolbieznosci.tydzien5;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CallableLesson {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("[" + Thread.currentThread().getName() + "] Starting...");

        Callable<Long> task = new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                System.out.println("[" + Thread.currentThread().getName() + "] Doing hard work..");
                throw new Exception("Trolo");
            }
        };

        ExecutorService executor = new ThreadPoolExecutor(4, 4,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>());

        final Future<Long> future = executor.submit(task);

//        Long result = future.get();
//
//        System.out.println("Computation result is: " + result);

        System.out.println("[" + Thread.currentThread().getName() + "] DONE");
    }
}

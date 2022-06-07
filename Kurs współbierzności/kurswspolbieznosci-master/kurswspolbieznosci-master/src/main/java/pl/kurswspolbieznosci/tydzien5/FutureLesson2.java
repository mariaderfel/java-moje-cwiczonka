package pl.kurswspolbieznosci.tydzien5;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class FutureLesson2 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        System.out.println("[" + Thread.currentThread().getName() + "] Starting...");
        ExecutorService es = new ThreadPoolExecutor(4, 4,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>());
        MyTask myTask1 = new MyTask(10);
        MyTask myTask2 = new MyTask(11);

//        Future<Long> future1 = es.submit(myTask1);
//        Future<Long> future2 = es.submit(myTask2);

        final List<Future<Long>> result = es.invokeAll(Arrays.asList(myTask1, myTask2));

        for (Future<Long> future : result) {
            System.out.println("[" + Thread.currentThread().getName() + "] Result is: " + future.get());
        }


        System.out.println("[" + Thread.currentThread().getName() + "] DONE");
    }

    static class MyTask implements Callable<Long> {
        long value;

        public MyTask(long value) {
            this.value = value;
        }

        @Override
        public Long call() throws Exception {
            if(value <= 0) {
                throw new IllegalArgumentException("Value cannot be lower than 1");
            }
            System.out.println("[" + Thread.currentThread().getName() + "] Starting hard work...");
            Thread.sleep(500);
            System.out.println("[" + Thread.currentThread().getName() + "] Done, returning result...");
            return value * value;
        }
    }
}

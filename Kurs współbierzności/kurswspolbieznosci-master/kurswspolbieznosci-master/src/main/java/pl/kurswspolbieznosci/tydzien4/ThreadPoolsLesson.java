package pl.kurswspolbieznosci.tydzien4;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolsLesson {
    public static void main(String[] args) {
        System.out.println("[" + Thread.currentThread().getName() + "]Starting...");

//        ExecutorService executor = new Executors.FinalizableDelegatedExecutorService
//            (new ThreadPoolExecutor(1, 1,
//                0L, TimeUnit.MILLISECONDS,
//                new LinkedBlockingQueue<Runnable>()));

        ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(4);

        Runnable task = new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("[" + Thread.currentThread().getName() + "-" + LocalDateTime.now().toString() +"] Doing hard work...");
                    Thread.sleep(2_000);
                    System.out.println("[" + Thread.currentThread().getName() + "-" + LocalDateTime.now().toString() +"] Job done, go home");
                } catch (InterruptedException e) {
                    throw new IllegalStateException(e);
                }
            }
        };

//        scheduledExecutor.schedule(task, 500, TimeUnit.MILLISECONDS);
        scheduledExecutor.scheduleAtFixedRate(task, 0, 500, TimeUnit.MILLISECONDS);
//        scheduledExecutor.scheduleWithFixedDelay(task, 200, 500, TimeUnit.MILLISECONDS);


        System.out.println("[" + Thread.currentThread().getName() + "]DONE");
    }
}

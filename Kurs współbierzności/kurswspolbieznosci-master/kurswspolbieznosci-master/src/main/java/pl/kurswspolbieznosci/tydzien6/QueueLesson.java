package pl.kurswspolbieznosci.tydzien6;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class QueueLesson {
    public static void main(String[] args) throws InterruptedException {
        print("Starting...");

        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(1);

        Runnable producer = new Runnable() {
            @Override
            public void run() {
                Random random = new Random();
                try {
                    for (int i = 0; i < 10; ++i) {
                        Thread.sleep(500);
                        int number = random.nextInt(100);
                        print("Adding number: " + number);
                        queue.put(number);
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
                    for (int i = 0; i < 100; ++i) {
                        int number = queue.take();
                        print("Read number: " + number);
                    }
                } catch (Exception e) {
                    throw new IllegalStateException(e);
                }
            }
        };

        ExecutorService service = new ThreadPoolExecutor(4, 4,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>());

        service.execute(producer);
        service.execute(producer);
        service.execute(consumer);

        service.shutdown();
        service.awaitTermination(5, TimeUnit.SECONDS);

        print("DONE");
    }


    static void print(Object output) {
        System.out.println(
            String.format("%s: %s - %s", LocalDateTime.now(), Thread.currentThread().getName(), output)
        );
    }
}

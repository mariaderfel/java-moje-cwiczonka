package pl.kurswspolbieznosci.tydzien6;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class FancyQueuesLesson {
    public static void main(String[] args) {
        print("Starting...");

        Exchanger<Integer> exchanger = new Exchanger<>();

        Runnable writer = new Runnable() {
            @Override
            public void run() {
                Random random = new Random();
                try {
                    for (int i = 0; i < 10; ++i) {
                        final int value = random.nextInt(100);
                        print("Putting value to exchanger: " + value);
                        int newValue = exchanger.exchange(value);
                        print("Received value is: " + newValue);
                    }
                } catch (InterruptedException e) {
                    throw new IllegalStateException(e);
                }
            }
        };

        ExecutorService service = new ThreadPoolExecutor(4, 4,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>());

        service.execute(writer);
        service.execute(writer);

        print("DONE");
    }


    static void print(Object output) {
        System.out.println(
            String.format("%s: %s - %s", LocalDateTime.now(), Thread.currentThread().getName(), output)
        );
    }
}

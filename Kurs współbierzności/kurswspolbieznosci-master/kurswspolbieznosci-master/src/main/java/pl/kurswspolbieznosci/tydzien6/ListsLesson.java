package pl.kurswspolbieznosci.tydzien6;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ListsLesson {
    public static void main(String[] args) throws InterruptedException {
        print("Starting...");

        List<Integer> list = Collections.unmodifiableList(new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)));

        print(list);

        Runnable reader = new Runnable() {
            @Override
            public void run() {
                try {
                    for (Integer integer : list) {
                        print("Read item from list: " + integer);
                        Thread.sleep(100);
                    }
                } catch (InterruptedException e) {
                    throw new IllegalStateException(e);
                }
            }
        };

        Runnable writer = new Runnable() {
            @Override
            public void run() {
                Random random = new Random();
                try {
                    for (int i = 0; i < 10; ++i) {
                        list.add(i, random.nextInt(20));
                        Thread.sleep(100);
                    }
                } catch (InterruptedException e) {
                    throw new IllegalStateException(e);
                }
            }
        };

        ExecutorService service = new ThreadPoolExecutor(4, 4,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>());

        service.execute(reader);
        service.execute(writer);
        service.execute(reader);
        service.execute(writer);
        service.execute(reader);
        service.execute(writer);

        service.shutdown();
        service.awaitTermination(5, TimeUnit.SECONDS);

        print(list);

        print("DONE");
    }


    static void print(Object output) {
        System.out.println(
            String.format("%s: %s - %s", LocalDateTime.now(), Thread.currentThread().getName(), output)
        );
    }
}

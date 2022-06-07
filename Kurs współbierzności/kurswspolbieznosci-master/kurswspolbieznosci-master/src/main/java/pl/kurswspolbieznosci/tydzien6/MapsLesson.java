package pl.kurswspolbieznosci.tydzien6;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MapsLesson {
    public static void main(String[] args) throws InterruptedException {
        print("Starting...");

        Map<String, Integer> map = new ConcurrentHashMap<>();

        Runnable task = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1_000; ++i) {
                    int size = map.size();
                    map.put(String.valueOf(size), size);
                }
            }
        };

        ExecutorService service = new ThreadPoolExecutor(4, 4,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>());

        service.submit(task);
        service.submit(task);
        service.submit(task);
        service.submit(task);

        service.shutdown();
        service.awaitTermination(5, TimeUnit.SECONDS);

        print("Result is: " + map.size());

        print("DONE");
    }

    static void print(String output) {
        System.out.println(
            String.format("%s: %s - %s", LocalDateTime.now(), Thread.currentThread().getName(), output)
        );
    }

}

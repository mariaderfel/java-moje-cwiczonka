package pl.kurswspolbieznosci.tydzien7;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Gauge;
import com.codahale.metrics.MetricRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MonitoringLesson {
    private static final Logger logger = LoggerFactory.getLogger(MonitoringLesson.class);

    public static void main(String[] args) throws InterruptedException {
        logger.info("Starting...");
        MetricRegistry metrics = new MetricRegistry();
        ConsoleReporter reporter = ConsoleReporter.forRegistry(metrics).build();
        reporter.start(1, TimeUnit.SECONDS);

        final BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(100);

        metrics.register("cachedexecutor.queue", new Gauge<Integer>() {
            @Override
            public Integer getValue() {
                return queue.size();
            }
        });


        ThreadPoolExecutor executor = new ThreadPoolExecutor(
            1, 1,
            0, TimeUnit.MILLISECONDS,
            queue
        );

        Runnable longTask = new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("Doing hard work...");
                    Thread.sleep(5_000);
                    System.out.println("DONE");
                } catch (InterruptedException e) {
                    throw new IllegalStateException(e);
                }
            }
        };

        for (int i = 0; i < 100; ++i) {
            executor.execute(longTask);
            Thread.sleep(200);
        }

        logger.info("DONE");
    }
}

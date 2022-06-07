package pl.kurswspolbieznosci.tydzien7;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ErrorsHandlingLesson {
    private static final Logger logger = LoggerFactory.getLogger(ErrorsHandlingLesson.class);

    public static void main(String[] args) throws InterruptedException {
        logger.info("Starting...");

        Runnable longTask = new Runnable() {
            @Override
            public void run() {
                try {
                    logger.info("Starting long task...");
                    Thread.sleep(1_000);
                    logger.info("DONE");
                } catch (InterruptedException e) {
                    throw new IllegalStateException(e);
                }
            }
        };

        ThreadPoolExecutor executor = new ThreadPoolExecutor(
            1,
            1,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(1),
            new RejectedExecutionHandler() {
                @Override
                public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                    logger.error("Task cannot be executed on thread pool: {}", executor);
                }
            }
        );

        executor.execute(longTask); // running
        executor.execute(longTask); // queued
        executor.execute(longTask); // rejected

        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);


        logger.info("DONE");
    }
}

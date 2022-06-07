package pl.kurswspolbieznosci.tydzien7;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;

public class ExceptionsLesson {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionsLesson.class);

    public static void main(String[] args) throws InterruptedException {
        logger.info("Starting...");

//        Runnable task = new Runnable() {
//            @Override
//            public void run() {
//                logger.info("Doing hard work...");
//                throw new RuntimeException("Oops...");
//            }
//        };
//
//        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
//            @Override
//            public void uncaughtException(Thread t, Throwable e) {
//                logger.error("Got uncaught exception from thread: {}, error: {}", t.getName(), e.getMessage());
//            }
//        });
//
//        Thread t0 = new Thread(task);
//        t0.start();
//
//        t0.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
//            @Override
//            public void uncaughtException(Thread t, Throwable e) {
//                logger.error("CUSTOM EXCEPTION HANDLER:", e);
//            }
//        });
//
//        ExecutorService es = Executors.newFixedThreadPool(4, new ThreadFactory() {
//            @Override
//            public Thread newThread(Runnable r) {
//                Thread t0 = new Thread(r);
//                t0.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
//                    @Override
//                    public void uncaughtException(Thread t, Throwable e) {
//                        logger.error("CUSTOM EXCEPTION HANDLER:", e);
//                    }
//                });
//                return t0;
//            }
//        });


        Callable<Long> compute = new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                logger.info("Computing big number...");
                Thread.sleep(500);
                throw new RuntimeException("Cant compute a value");
            };
        };

        ExecutorService service = Executors.newFixedThreadPool(4);
        final Future<Long> submit = service.submit(compute);

        logger.info("Going to sleep...");
        Thread.sleep(2_000);
        logger.info("OK!");

        try {
            submit.get();
        } catch (ExecutionException e) {
            logger.error("Exception from future: {}", e.getMessage(), e);
        }


        logger.info("DONE");
    }
}

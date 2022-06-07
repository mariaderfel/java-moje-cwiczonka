package pl.kurswspolbieznosci.tydzien5;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CompletableFutureLesson1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("[" + Thread.currentThread().getName() + "] Starting...");

        CompletableFuture<Long> cf = new CompletableFuture<Long>();

        Runnable task = new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("[" + Thread.currentThread().getName() + "] Doing hard work..");
                    Thread.sleep(500);
                    cf.complete(42L);
                } catch (InterruptedException e) {
                    throw new IllegalStateException(e);
                }
            }
        };


        CompletableFuture<String> future = CompletableFuture.completedFuture("I've already got this one");

        final CompletableFuture<Long> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("[" + Thread.currentThread().getName() + "] Doing hard work..");
                Thread.sleep(500);
                return 123L;
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
        });

        Long result = future1.get();

        System.out.println("[" + Thread.currentThread().getName() + "] Result is: " + result);

        System.out.println("[" + Thread.currentThread().getName() + "] DONE");
    }
}

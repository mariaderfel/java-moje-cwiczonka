package pl.kurswspolbieznosci.tydzien5;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CompletableFutureLesson5 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("[" + Thread.currentThread().getName() + "] Starting...");

        CompletableFuture<Long> asyncLong = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("[" + Thread.currentThread().getName() + "] Computing...");
                Thread.sleep(500);
                System.out.println("[" + Thread.currentThread().getName() + "] DONE");
                return 42l;
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
        });

        CompletableFuture<Long> asyncLong2 = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("[" + Thread.currentThread().getName() + "] Computing...");
                Thread.sleep(1500);
                System.out.println("[" + Thread.currentThread().getName() + "] DONE");
                return 42l;
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
        });

        Executor executor = Executors.newSingleThreadExecutor();

        final CompletableFuture<Void> future = asyncLong.thenAcceptAsync(value -> {
            try {
                System.out.println("[" + Thread.currentThread().getName() + "] Accepting value...");
                Thread.sleep(200);
                System.out.println("[" + Thread.currentThread().getName() + "] Consumed value is: " + value);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
        }, executor);

        asyncLong.get();

        System.out.println("[" + Thread.currentThread().getName() + "] DONE");

        CompletableFuture<Long> cf = new CompletableFuture<>();
        cf.complete(99L);
        cf.completeExceptionally(new RuntimeException("Oops..."));
        System.out.println("Result is: " + cf.get());
    }
}

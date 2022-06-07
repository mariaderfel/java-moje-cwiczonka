package pl.kurswspolbieznosci.tydzien5;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class CompletableFutureLesson3 {
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
                Thread.sleep(100);
                System.out.println("[" + Thread.currentThread().getName() + "] DONE");
                return 97l;
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
        });


        CompletableFuture<String> asyncString = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("[" + Thread.currentThread().getName() + "] Computing...");
                Thread.sleep(10);
                System.out.println("[" + Thread.currentThread().getName() + "] DONE");
                return "Consumer name is: John Doe";
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
        });


//        final CompletableFuture<String> asyncTask = asyncLong.thenCombine(asyncString, new BiFunction<Long, String, String>() {
//            @Override
//            public String apply(Long number, String text) {
//                return "Combined value is: " + text + " - " + number;
//            }
//        });

        final CompletableFuture<String> asyncTask = asyncLong.thenCompose(new Function<Long, CompletionStage<String>>() {
            @Override
            public CompletionStage<String> apply(Long value) {
                return asyncAnswer(value);
            }
        });

//        final CompletableFuture<Void> future = asyncLong.acceptEither(asyncLong2, value -> {
//            System.out.println("The faster one was: " + value);
//        });


        final CompletableFuture<Void> future = asyncLong.thenAcceptBoth(asyncLong2, new BiConsumer<Long, Long>() {
            @Override
            public void accept(Long aLong, Long aLong2) {
                System.out.println("The values are: " + aLong + " and: " + aLong2);
            }
        });
//        final CompletableFuture<Object> future1 = CompletableFuture.anyOf(asyncLong, asyncLong2, asyncString);

        final CompletableFuture<Void> future1 = CompletableFuture.allOf(asyncLong, asyncLong2, asyncString);
        System.out.println("[" + Thread.currentThread().getName() + "] Async Result is: " + future1.get());

        System.out.println("value is: " + asyncLong.get());
        System.out.println("value is: " + asyncLong2.get());
        System.out.println("value is: " + asyncString.get());

        System.out.println("[" + Thread.currentThread().getName() + "] DONE");
    }

    static CompletableFuture<String> asyncAnswer(Long value) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("[" + Thread.currentThread().getName() + "] Computing...");
                Thread.sleep(500);
                System.out.println("[" + Thread.currentThread().getName() + "] DONE");
                return "Answer to all questions is: " + value;
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
        });
    }
}

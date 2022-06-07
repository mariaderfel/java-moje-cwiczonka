package pl.kurswspolbieznosci.tydzien5;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.function.BiFunction;
import java.util.function.Function;

public class CompletableFutureLesson2 {
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


        CompletableFuture<String> asyncString = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("[" + Thread.currentThread().getName() + "] Computing...");
                Thread.sleep(500);
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



        System.out.println("[" + Thread.currentThread().getName() + "] Async Result is: " + asyncTask.get());


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

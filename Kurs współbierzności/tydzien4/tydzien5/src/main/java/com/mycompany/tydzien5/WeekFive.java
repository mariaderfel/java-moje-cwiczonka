
package com.mycompany.tydzien5;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class WeekFive {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture<String> klockiAsync = new AsyncTask("klocki").fetch();
        CompletableFuture<String> tasmaAsync = new AsyncTask("tasma").fetch();
        CompletableFuture<String> papierAsync = new AsyncTask("papier").fetch();

        final CompletableFuture<List<String>> future = klockiAsync.thenCombine(tasmaAsync, (klocki, tasma) -> {
            List<String> lista = new ArrayList<>();
            lista.add(klocki);
            lista.add(tasma);
            return lista;
        }).thenCombine(papierAsync, (lista, papier) -> {
            lista.add(papier);
            return lista;
        }).whenComplete((lista, error) -> {
            if (error != null) {
                System.err.println("[" + Thread.currentThread().getName() + "] Somehting went wrong. Unable to proceed with your order");
            } else {
                System.out.println("[" + Thread.currentThread().getName() + "] Parcel: [" + String.join(", ", lista) + "]");
            }
        });

        future.get();
    }

    static class AsyncTask {
        private String result;

        public AsyncTask(String result) {
            this.result = result;
        }

        public CompletableFuture<String> fetch() {
            Random number = new Random();
            return CompletableFuture.supplyAsync(() -> {
                if (number.nextInt(100) % 3 == 0) {
                    throw new RuntimeException("Unfortunately we can't complete your order");
                }
                try {
                    System.out.println("[" + Thread.currentThread().getName() + "]Preparing result: " + result + "...");
                    Thread.sleep(500);
                    System.out.println("[" + Thread.currentThread().getName() + "] DONE [" + result + "]");
                    return result;
                } catch (InterruptedException e) {
                    throw new IllegalStateException(e);
                }
            });
        }
    }
}

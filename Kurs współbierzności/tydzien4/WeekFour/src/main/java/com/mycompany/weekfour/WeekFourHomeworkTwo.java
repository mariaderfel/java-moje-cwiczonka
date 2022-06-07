/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.weekfour;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class WeekFourHomeworkTwo {

    public static void main(String[] args) {
        System.out.println("[" + Thread.currentThread().getName() + "] Start");

        ForkJoinPool joinPool = new ForkJoinPool(10);

        String number = "785211556421";

        Integer sum = joinPool.invoke(new CountingTask(number));

        System.out.println("Sum of number is: " + sum);
        System.out.println("[" + Thread.currentThread().getName() + "] Done");
    }

    static class CountingTask extends RecursiveTask<Integer> {

        public String number;

        public CountingTask(String number) {
            this.number = number;
        }

        @Override
        protected Integer compute() {
            Integer sum = 0;

            if (number.length() == 1) {
                return Integer.valueOf(number);
            } else {
                List<CountingTask> taskList = new ArrayList<>();

                String[] parts = {number.substring(0, number.length() / 2), number.substring(number.length() / 2)};

                for (String part : parts) {
                    taskList.add(new CountingTask(part));
                }

                for (CountingTask task : taskList) {
                    task.fork();
                }

                for (CountingTask task : taskList) {
                    sum = sum + task.join();
                }
            }
            return sum;
        }
    }
}

package pl.kurswspolbieznosci.tydzien3;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LockLesson {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Starting...");

//        Counter counter = new Counter();
//
//        Runnable task = new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 10_000; ++i) {
//                    counter.increment();
//                }
//            }
//        };
//
//        Thread t0 = new Thread(task);
//        Thread t1 = new Thread(task);
//        Thread t2 = new Thread(task);
//
//        t0.start();
//        t1.start();
//        t2.start();
//
//        t0.join();
//        t1.join();
//        t2.join();

//        System.out.println("Counter value is: " + counter.value);


        Inventory inventory = new Inventory();

        Runnable writeTask = () -> {
            try {
                for (int i = 0; i < 10; ++i) {
                    inventory.put("pomidor", i);
                }
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
        };

//        Runnable readTask = () -> {
//            for (int i = 0; i < 10; ++i) {
//                Integer quantity = inventory.howMany("pomidor");
//                System.out.println("There is " + quantity + " of pomidors");
//            }
//        };

        Thread t0 = new Thread(writeTask);
        Thread t1 = new Thread(writeTask);
//        Thread t1 = new Thread(readTask);
//        Thread t2 = new Thread(readTask);
//        Thread t3 = new Thread(readTask);

        t0.start();
        t1.start();
//        t2.start();
//        t3.start();

        t0.join();
        t1.join();
//        t2.join();
//        t3.join();

        System.out.println("[" + Thread.currentThread().getName() + "] There is: " + inventory.howMany("pomidor") + " of pomidor in inventory");

        System.out.println("Done");
    }

    static class Inventory {
        Map<String, Integer> state = new HashMap<>();
        Lock lock = new ReentrantLock();

        public void put(String item, Integer quantity) throws InterruptedException {
            boolean tryLock = lock.tryLock(100, TimeUnit.MILLISECONDS);
            if (tryLock) {
                try {
                    System.out.println("[" + Thread.currentThread().getName() + "] Got lock...");
                    Thread.sleep(100);
                    System.out.println("[" + Thread.currentThread().getName() + "] There is "+ howMany("pomidor") + " of pomidors in inventory");
                    state.put(item, quantity);
                } finally {
                    lock.unlock();
                }
            } else {
                System.out.println("[" + Thread.currentThread().getName() + "] Unable to get lock...");
            }
        }

        public Integer howMany(String item) {
            if(lock.tryLock()) {
                try {
                    return state.get(item);
                } finally {
                    lock.unlock();
                }
            } else {
                return -1;
            }
        }
    }

    static class Counter {
        long value = 0;
        Lock lock = new ReentrantLock();

        public void increment() {
            try {
                lock.lock();
                value = value + 1;
                if (value == 1000) {
                    throw new IllegalStateException("Ups...");
                }
            } finally {
                lock.unlock();
            }
        }
    }
}

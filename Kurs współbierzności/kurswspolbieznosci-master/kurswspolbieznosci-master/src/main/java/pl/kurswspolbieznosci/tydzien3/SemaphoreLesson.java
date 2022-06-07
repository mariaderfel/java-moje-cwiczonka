package pl.kurswspolbieznosci.tydzien3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class SemaphoreLesson {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Starting...");

        Restaurant restaurant = new Restaurant(5);

        Runnable task = new Runnable() {
            @Override
            public void run() {
                try {
                    Person person = new Person();
                    boolean success = restaurant.stepInto(person);
                    if (success) {
                        System.out.println("[" + Thread.currentThread().getName() + "] I enjoy this place");
                        Thread.sleep(1_000);
                        restaurant.leave(person);
                    } else {
                        System.out.println("[" + Thread.currentThread().getName() + "] no more seats :(");
                    }
                } catch (InterruptedException e) {
                    throw new IllegalStateException(e);
                }
            }
        };

        Thread t0 = new Thread(task, "PERS1");
        Thread t1 = new Thread(task, "PERS2");
        Thread t2 = new Thread(task, "PERS3");
        Thread t3 = new Thread(task, "PERS4");
        Thread t4 = new Thread(task, "PERS5");
        Thread t5 = new Thread(task, "PERS6");
        Thread t6 = new Thread(task, "PERS7");
        Thread t7 = new Thread(task, "PERS8");
        Thread t8 = new Thread(task, "PERS9");
        Thread t9 = new Thread(task, "PERS10");

        t0.start();
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
        t8.start();
        t9.start();

        t0.join();
        t1.join();
        t2.join();
        t3.join();
        t4.join();
        t5.join();
        t6.join();
        t7.join();
        t8.join();
        t9.join();

        System.out.println("Done");
    }

    static class Person {

    }

    static class Restaurant {
        int seats;
        List<Person> personList = new ArrayList<>();
        Semaphore semaphore;

        public Restaurant(int seats) {
            semaphore = new Semaphore(1);
            this.seats = seats;
        }

        public boolean stepInto(Person person) {
            System.out.println("[" + Thread.currentThread().getName() + "] stepping into restaurant...");
            boolean acquired = semaphore.tryAcquire();
            if(acquired) {
                System.out.println("[" + Thread.currentThread().getName() + "] Eating...");
                personList.add(person);
            } else {
                System.out.println("There are already " + personList.size() + " people in here");
            }
            return acquired;
        }

        public void leave(Person person) {
            if(personList.remove(person)) {
                semaphore.release();
            }
        }


    }

}

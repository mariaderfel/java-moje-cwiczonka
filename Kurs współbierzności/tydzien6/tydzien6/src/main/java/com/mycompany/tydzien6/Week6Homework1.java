
package com.mycompany.tydzien6;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Week6Homework1 {

    public static void main(String[] args) throws InterruptedException {

        Map<Integer, MyObjectForWork> map = new ConcurrentHashMap<>();
        for (int i = 0; i < 100; i++) {
            map.put(i, new MyObjectForWork());
        }

        ExecutorService service = new ThreadPoolExecutor(4, 4,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());

        Runnable worker = new Runnable() {
            @Override
            public void run() {
                try {
                    Random random = new Random();
                    for (int i = 0; i < 30; i++) {
                        Integer key = random.nextInt(100);
                        MyObjectForWork element = map.get(key);
                        if (element != null) {
                            System.out.println("[" + Thread.currentThread().getName() + "]map element " + key + "  value: " + element.getValue());
                            element.setValue(element.getValue() + 1);
                            element.setMoment(LocalDateTime.now());
                            map.put(key, element);
                            System.out.println("[" + Thread.currentThread().getName() + "]map element " + key + " NEW value: " + element.getValue());
                            Thread.sleep(100);
                        }
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(Week6Homework1.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };

        Runnable cleaner = new Runnable() {
            @Override
            public void run() {
                LocalDateTime currentlyMoment = LocalDateTime.now();
                System.out.println("[" + Thread.currentThread().getName() + "] clean map ");
                try {
                    for (int i = 0; i < map.size(); i++) {
                        if (map.get(i) != null) {
                            if (Duration.between(map.get(i).getMoment(), currentlyMoment).getSeconds() > 10) {
                                System.out.println("[" + Thread.currentThread().getName() + "]map element " + i + " unused since "
                                        + Duration.between(map.get(i).getMoment(), currentlyMoment).getSeconds() + " seconds !!! REMOVED !!!");
                                map.remove(i);
                            }
                        }
                    }
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Week6Homework1.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };

        long current = System.currentTimeMillis();
        long tempCurent1 = System.currentTimeMillis();
        long tempCurent2 = System.currentTimeMillis();

        while (current + 30000 >= System.currentTimeMillis()) {
            if (tempCurent1 + 5000 == System.currentTimeMillis()) {
                service.execute(worker);
                tempCurent1 = System.currentTimeMillis();
            }
            if (tempCurent2 + 10000 == System.currentTimeMillis()) {
                service.execute(cleaner);
                tempCurent2 = System.currentTimeMillis();
            }
        }
        service.shutdown();
    }
}

class MyObjectForWork {

    private int value;
    private LocalDateTime moment;

    public MyObjectForWork() {
        Random random = new Random();
        this.value = random.nextInt(10);
        this.moment = LocalDateTime.now();
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public LocalDateTime getMoment() {
        return moment;
    }

    public void setMoment(LocalDateTime moment) {
        this.moment = moment;
    }

}

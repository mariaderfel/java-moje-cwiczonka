package pl.kurswspolbieznosci.tydzien3;

public class LivelockLesson {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Starting...");
        Object spoon = new Object();
        Person wife = new Person(spoon);
        Person husband = new Person(spoon);

        Thread t0 = new Thread(new Runnable() {
            @Override
            public void run() {
                wife.eatWith(husband);
            }
        }, "WIFE");

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                husband.eatWith(wife);
            }
        }, "HUSBAND");

        t0.start();
        t1.start();

        t0.join();
        t1.join();

        System.out.println("DONE");
    }

    static class Person {
        Object spoon;
        boolean isHungry = true;

        public Person(Object spoon) {
            this.spoon = spoon;
        }

        public void eatWith(Person partner) {
            while (isHungry) {
                synchronized (spoon) {
                    System.out.println("[" + Thread.currentThread().getName() + "] Eating...");
                    isHungry = false;
                }
            }
        }
    }
}

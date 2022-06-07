package pl.kurswspolbieznosci.tydzien3;

public class SynchronizedLesson {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Starting...");

        Counter counter = new Counter();
        Counter otherCounter = new Counter();

        Runnable incrementTask = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100_000; ++i) {
                    counter.incrementInstances();
                    otherCounter.incrementInstances();
                }
            }
        };

        Thread t0 = new Thread(incrementTask);
        Thread t1 = new Thread(incrementTask);
        Thread t2 = new Thread(incrementTask);

        t0.start();
        t1.start();
        t2.start();

        t0.join();
        t1.join();
        t2.join();

        System.out.println("Counter value is: " + counter.instances);

        System.out.println("Done");
    }

    static class Counter {
        private long value = 0;
        private long otherValue = 0;
        private long noSynchronizedValue = 0;
        private static long instances = 0;

        public static void incrementInstances() {
            instances = instances + 1;
        }

        public void increment() {
            synchronized (this) {
                long tmp = value;
                tmp = tmp + 1;
                value = tmp;
            }
        }

        public synchronized void decrement() {
            long tmp = otherValue;
            tmp = tmp - 1;
            otherValue = tmp;
        }
    }
}

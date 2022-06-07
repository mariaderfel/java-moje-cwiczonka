
import java.util.Random;

public class WeekTwo {

    public static final int MAX50 = 50;
    public static final int MAX500 = 500;

    public static void main(String[] args) throws InterruptedException {
        Number number = new Number();

        Thread t1 = new Thread(new CreateNumberTask(number, MAX50), "t1 - Create 50");
        Thread t2 = new Thread(new CreateNumberTask(number, MAX500), "t2 - Create 500");
        Thread t3 = new Thread(new ReaderNumberTask(number), "t3 - Sum number");
        Thread t4 = new Thread(new MonitorsThread(t1, t2, t3, number), "t4 - Monitor");

        t1.start();
        t2.start();
        t3.start();
        t4.setDaemon(true);
        t4.start();

        Thread.sleep(30000);
        t1.interrupt();
        t2.interrupt();
        t3.interrupt();
    }

    static class Number {
        public Integer number = null;

        public Integer getNumber() {
            return number;
        }

        public synchronized void drawNumber(int max) throws InterruptedException {
            while (number != null) {
                wait(1_000);
            }
            number = randomNumber(max);
            System.out.println("Drawn number for the thread [" + Thread.currentThread().getName() + "] = " + number);
            notifyAll();
        }

        public Integer randomNumber(int max) {
            Integer liczba;
            Random generator = new Random();
            return liczba = max == MAX50 ? generator.nextInt(50) + 1 : generator.nextInt(400) + 101;
        }

        public synchronized int addUp() throws InterruptedException {
            int sum = 0;
            while (number == null) {
                wait(1_000);
            }
            for (int i = 0; i <= number; i++) {
                sum = sum + i;
            }
            System.out.println("The sum of the number for the thread [" + Thread.currentThread().getName() + "] = " + sum);
            number = null;
            notifyAll();
            return sum;
        }
    }

    static class CreateNumberTask implements Runnable {

        Number number;
        private int max;

        public CreateNumberTask(Number number, int max) {
            this.number = number;
            this.max = max;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    number.drawNumber(max);
                    Thread.sleep(1_000);
                }
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
        }
    }

    static class ReaderNumberTask implements Runnable {
        Number number;

        public ReaderNumberTask(Number number) {
            this.number = number;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    number.addUp();
                    Thread.sleep(1_000);
                }
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
        }
    }

    static class MonitorsThread implements Runnable {
        Thread t1;
        Thread t2;
        Thread t3;
        Number number;

        public MonitorsThread(Thread t1, Thread t2, Thread t3, Number number) {
            this.t1 = t1;
            this.t2 = t2;
            this.t3 = t3;
            this.number = number;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    System.out.println("Thread [" + t1.getName() + "] is in state " + t1.getState());
                    System.out.println("Thread [" + t2.getName() + "] is in state " + t2.getState());
                    System.out.println("Thread [" + t3.getName() + "] is in state " + t3.getState());
                    System.out.println("Number " + number.getNumber());
                    Thread.sleep(1_000);
                }
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}

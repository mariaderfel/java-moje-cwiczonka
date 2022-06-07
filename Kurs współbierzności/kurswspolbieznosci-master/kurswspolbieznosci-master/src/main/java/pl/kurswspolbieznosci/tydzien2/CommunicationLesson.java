package pl.kurswspolbieznosci.tydzien2;

import java.util.function.Consumer;

import javax.xml.crypto.Data;

public class CommunicationLesson {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Starting...");
        Data data = new Data();

        Thread t0 = new Thread(new WriterTask(data), "WriterThread");
        Thread t1 = new Thread(new ReaderTask(data, "ReaderOne"), "ReaderOneThread");
        Thread t2 = new Thread(new ReaderTask(data, "ReaderTwo"), "ReaderTwoThread");

        t0.start();
        t1.start();
        t2.start();

        System.out.println("Done");
    }

    static class Data {
        private String message = null;

        public synchronized void write(String msg) throws InterruptedException {
            while(message != null) {
//                System.out.println(Thread.currentThread().getName() + " waits...");
                wait(1_000);     // waiting for other thread to call notify()
//                System.out.println(Thread.currentThread().getName() + " WAKE UP!");
            }
            message = msg;
            notifyAll();
        }

        public synchronized String read() throws InterruptedException {
            while(message == null) {
//                System.out.println(Thread.currentThread().getName() + " waits...");
                wait(1_000);     // waiting for other thread to call notify()
//                System.out.println(Thread.currentThread().getName() + " WAKE UP!");
            }
            String tmp = message;
            message = null;
            notifyAll();
            return tmp;
        }
    }

    static class WriterTask implements Runnable {
        Data data;
        int counter = 0;

        public WriterTask(Data data) {
            this.data = data;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    counter++;
                    String message = "This is my message number: " + counter;
                    System.out.println("[Writer] Sending a message...");
                    data.write(message);
                    Thread.sleep(5_000);
                }
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
        }
    }

    static class ReaderTask implements Runnable {
        Data data;
        String name;

        public ReaderTask(Data data, String name) {
            this.data = data;
            this.name = name;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    String message = data.read();
                    System.out.println("==" + name + "== got message: " + message);
                    Thread.sleep(2_500);
                }
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
        }
    }

}

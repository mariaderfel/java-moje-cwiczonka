package pl.kurswspolbieznosci.tydzien2;

public class StoppingLesson {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Starting...");
        Telemetric telemetric = new Telemetric();

        Thread t0 = new Thread(telemetric);
        t0.start();
        Thread.sleep(5_000);
        System.out.println("Done");

        t0.interrupt();

        t0.join();
        System.out.println("Thread t0 state is: " + t0.getState());
    }

    static class Telemetric implements Runnable {
        @Override
        public void run() {
            while(true) {
                try {
                    Runtime runtime = Runtime.getRuntime();
                    System.out.println("Free memory is: " + (runtime.freeMemory()) / 1024 + " KB ...");
                    Thread.sleep(1_000);
                } catch (InterruptedException e) {
                    System.err.println("Got Interrupted Exception, stopping my work...");
                    break;
                }
            }
        }
    }

}

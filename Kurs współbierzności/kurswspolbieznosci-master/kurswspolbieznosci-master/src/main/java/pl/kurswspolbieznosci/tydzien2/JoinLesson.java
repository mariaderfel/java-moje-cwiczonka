package pl.kurswspolbieznosci.tydzien2;

public class JoinLesson {
    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " = Starting...");


        Runnable task = new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 10; ++i) {
                        System.out.println(Thread.currentThread().getName() + " = I am running in a thread...");
                        Thread.sleep(1_000);
                    }
                } catch (InterruptedException e) {
                    throw new IllegalStateException(e);
                }
            }
        };

        Thread t0 = new Thread(task);
        t0.start();
        t0.join();

        System.out.println(Thread.currentThread().getName() + " = Done");
    }
}

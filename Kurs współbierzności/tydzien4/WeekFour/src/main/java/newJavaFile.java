import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class newJavaFile {

    public static final String BIG_NUMBER = "129076943110205738494672";
    public static final int PARALLELISM_LEVEL = 5;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("[" + Thread.currentThread().getName() + "] Starting...");

        ForkJoinPool joinPool = new ForkJoinPool(PARALLELISM_LEVEL);

        Integer sum = joinPool.invoke(new SumTask(BIG_NUMBER));
        System.out.println("Sum of digids: " + sum);
        System.out.println("[" + Thread.currentThread().getName() + "] DONE");
    }

    private static class SumTask extends RecursiveTask<Integer> {
        private String bigNumber;

        public SumTask(String bigNumber) {
            this.bigNumber = bigNumber;
        }

        @Override
        protected Integer compute() {
            System.out.println("[" + Thread.currentThread().getName() + "] Computing value... " + bigNumber);
            if (bigNumber.length() == 1) {
                return Integer.valueOf(bigNumber);
            } else {
                List<SumTask> tasks = new ArrayList<>();
                
                final int mid = bigNumber.length() / 2;
                
                String[] parts = {bigNumber.substring(0, mid), bigNumber.substring(mid)};
                
                for (String child : parts) {
                    tasks.add(new SumTask(child));
                }

                for (SumTask task : tasks) {
                    task.fork();
                }

                int sum = 0;
                for (SumTask task : tasks) {
                    sum = sum + task.join();
                }
                return sum;
            }
        }
    }
}

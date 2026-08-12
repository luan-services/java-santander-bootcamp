public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== 1. basic thread example ===");
        BasicThreadExample.runExample();

        System.out.println("\n=== 2. runnable example ===");
        RunnableExample.runExample();

        System.out.println("\n=== 3. start vs run example ===");
        StartVsRunExample.runExample();

        System.out.println("\n=== 4. sleep and join example ===");
        SleepAndJoinExample.runExample();

        System.out.println("\n=== 5. synchronized example ===");
        SynchronizedExample.runExample();

        System.out.println("\n=== 6. executor service example ===");
        ExecutorServiceExample.runExample();
    }
}

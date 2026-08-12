public class RunnableExample {

    public static void runExample() throws InterruptedException {
        // runnable represents the task that should be executed
        Runnable printTask = () -> {
            String threadName = Thread.currentThread().getName();

            System.out.println("task executed by " + threadName);
        };

        // thread receives the runnable task
        Thread firstThread = new Thread(printTask, "first-thread");
        Thread secondThread = new Thread(printTask, "second-thread");

        firstThread.start();
        secondThread.start();

        firstThread.join();
        secondThread.join();
    }
}

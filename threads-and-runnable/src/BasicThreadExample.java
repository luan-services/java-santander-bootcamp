public class BasicThreadExample {

    public static void runExample() throws InterruptedException {
        // creates a new thread with a task
        Thread workerThread = new Thread(() -> {
            // gets the name of the thread currently running this code
            String threadName = Thread.currentThread().getName();

            System.out.println(threadName + " is running");
        });

        // gives a readable name to the thread
        workerThread.setName("worker-thread");

        // starts a new execution flow
        workerThread.start();

        // waits for the worker thread to finish
        workerThread.join();

        System.out.println("main thread continues");
    }
}

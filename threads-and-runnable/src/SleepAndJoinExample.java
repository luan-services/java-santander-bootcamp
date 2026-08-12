public class SleepAndJoinExample {

    public static void runExample() throws InterruptedException {
        Thread workerThread = new Thread(() -> {
            for (int count = 1; count <= 3; count++) {
                System.out.println("worker step: " + count);

                try {
                    // pauses only the current thread
                    Thread.sleep(500);
                } catch (InterruptedException exception) {
                    // restores the interrupted status
                    Thread.currentThread().interrupt();

                    return;
                }
            }
        }, "sleep-worker");

        workerThread.start();

        // join makes the current thread wait for workerThread to finish
        workerThread.join();

        System.out.println("worker finished, main can continue");
    }
}

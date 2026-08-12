public class SynchronizedExample {

    public static void runExample() throws InterruptedException {
        Counter counter = new Counter();

        Runnable incrementTask = () -> {
            for (int count = 0; count < 100_000; count++) {
                counter.increment();
            }
        };

        // both threads share the same counter object
        Thread firstThread = new Thread(incrementTask, "counter-thread-1");
        Thread secondThread = new Thread(incrementTask, "counter-thread-2");

        firstThread.start();
        secondThread.start();

        firstThread.join();
        secondThread.join();

        System.out.println("expected value: 200000");
        System.out.println("actual value:   " + counter.getValue());
    }

    private static class Counter {
        private int value = 0;

        // synchronized allows only one thread at a time to enter this method
        public synchronized void increment() {
            value++;
        }

        public synchronized int getValue() {
            return value;
        }
    }
}

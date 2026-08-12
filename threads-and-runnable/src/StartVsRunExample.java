public class StartVsRunExample {

    public static void runExample() throws InterruptedException {
        Runnable task = () -> {
            System.out.println(
                "task is running on: " + Thread.currentThread().getName()
            );
        };

        Thread thread = new Thread(task, "new-thread");

        // run does not create a new thread
        System.out.println("calling run()");
        thread.run();

        // start creates a new execution flow and then calls run internally
        System.out.println("calling start()");
        thread.start();

        thread.join();
    }
}

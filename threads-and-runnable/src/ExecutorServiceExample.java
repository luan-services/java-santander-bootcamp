import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceExample {

    public static void runExample() throws InterruptedException {
        // creates a pool that can reuse two platform threads
        ExecutorService executor = Executors.newFixedThreadPool(2);

        Runnable firstTask = () -> printTask("first task");
        Runnable secondTask = () -> printTask("second task");
        Runnable thirdTask = () -> printTask("third task");

        // submit sends tasks to the executor instead of creating threads manually
        executor.submit(firstTask);
        executor.submit(secondTask);
        executor.submit(thirdTask);

        // prevents new tasks from being submitted
        executor.shutdown();

        // waits a limited amount of time for submitted tasks to finish
        executor.awaitTermination(5, TimeUnit.SECONDS);
    }

    private static void printTask(String taskName) {
        String threadName = Thread.currentThread().getName();

        System.out.println(taskName + " executed by " + threadName);
    }
}

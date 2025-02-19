package objprog.threads;

public class SequentialExample {
    public static void main(String[] args) {
        // Instantiate tasks
        MyRunnable myRunnableTask = new MyRunnable("Runnable-Task");
        MyThread myThreadTask = new MyThread("Thread-Task");

        System.out.println("Running tasks sequentially by calling run() directly:");

        // Call run() directly, not start(). These calls run sequentially on the main thread.
        myRunnableTask.run();
        myThreadTask.run();

        System.out.println("Sequential tasks complete.");
    }
}

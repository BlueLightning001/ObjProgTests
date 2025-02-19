package objprog.threads;

public class ThreadExample {
    public static void main(String[] args) {
        // Using a Runnable implementation
        Thread runnableThread = new Thread(new MyRunnable("Runnable-Thread"));
        runnableThread.start();

        // Using a class that extends Thread
        MyThread extendedThread = new MyThread("Extended-Thread");
        extendedThread.start();

        // Optionally wait for both threads to finish
        try {
            runnableThread.join();
            extendedThread.join();
        } catch (InterruptedException e) {
            System.err.println("Main thread interrupted!");
        }

        System.out.println("Both threads have finished execution.");
    }
}


package objprog.threads;

public class MyRunnable implements Runnable {
    private String name;

    public MyRunnable(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(name + " - iteration " + i);
            try {
                // Sleep for 1 second to simulate work
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.err.println(name + " was interrupted!");
            }
        }
    }
}

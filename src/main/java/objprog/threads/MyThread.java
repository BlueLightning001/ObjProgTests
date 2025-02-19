package objprog.threads;

public class MyThread extends Thread {
    public MyThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(getName() + " - iteration " + i);
            try {
                // Sleep for 1 second to simulate work
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.err.println(getName() + " was interrupted!");
            }
        }
    }
}

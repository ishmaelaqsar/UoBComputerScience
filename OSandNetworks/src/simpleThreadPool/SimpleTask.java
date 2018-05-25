package simpleThreadPool;

public class SimpleTask implements ISimpleTask {

    private int i;

    public SimpleTask(int i) {
        this.i = i;
    }

    public static void main(String args[]) {
        // Initialize thread pool
        SimpleThreadPool pool = new SimpleThreadPool();
        pool.start();
        // Create 25 tasks
        for (int i = 1; i <= 25; i++) {
            pool.addTask(new SimpleTask(i));
        }
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " says: I wish I could sleep for " + i + " hour(s)");
    }
}

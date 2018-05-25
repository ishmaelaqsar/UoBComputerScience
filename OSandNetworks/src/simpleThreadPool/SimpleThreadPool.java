package simpleThreadPool;

import java.util.concurrent.LinkedBlockingQueue;

public class SimpleThreadPool implements ISimpleThreadPool {

    private LinkedBlockingQueue<ISimpleTask> queue = new LinkedBlockingQueue<>();
    private Thread[] threads = new Thread[6];

    @Override
    public void start() {
        for (int i = 0; i < threads.length; i++) {
            //threads[i] = (new Thread(new SimplePoolThread(tasksQ)).start());
            threads[i] = (new Thread(new SimplePoolThread(queue))); // init the threads
            threads[i].start();         // start the workers
        }
    }

    @Override
    public void stop() {
        for (int i = 0; i < threads.length; i++) {
            //Thread.currentThread().interrupt();
            threads[i].interrupt(); // safely calls interrupt on the threads in order for them to stop
        }
    }

    @Override
    public void addTask(ISimpleTask task) {
        // add a task to be performed
        try {
            this.queue.put(task);
        } catch (InterruptedException e) {
            System.err.println("Task could not be added");
            e.printStackTrace();
        }
    }
}

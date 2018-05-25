package simpleThreadPool;

import java.util.concurrent.LinkedBlockingQueue;

public class SimplePoolThread implements ISimplePoolThread {

    private LinkedBlockingQueue<ISimpleTask> queue;

    public SimplePoolThread(LinkedBlockingQueue<ISimpleTask> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {

        while (!queue.isEmpty()) {
            try {
                queue.take().run();
                /*
                Retrieves and removes the head of this queue, waiting if necessary until an element becomes available.
                If this thread was constructed using a separate Runnable run object, then that Runnable object's run method is called;
                otherwise, this method does nothing and returns.
                 */
            } catch (InterruptedException e) {
                System.err.println("Tasks interrupted.");
                e.printStackTrace();
                break;
            }
        }
    }
}

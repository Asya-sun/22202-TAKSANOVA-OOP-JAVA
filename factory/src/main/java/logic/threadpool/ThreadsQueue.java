package logic.threadpool;

import java.util.ArrayDeque;

public class ThreadsQueue<detail> {
    protected ArrayDeque<detail> queue;
    public ThreadsQueue () {
        queue = new ArrayDeque<>();
    }

    synchronized public detail get() {
        while (queue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return queue.pop();
    }

    synchronized public void put(detail a) {
        queue.push(a);
        notify();
    }
}

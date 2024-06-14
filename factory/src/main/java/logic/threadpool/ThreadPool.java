package logic.threadpool;

import java.util.ArrayDeque;

public class ThreadPool extends Thread{
    private final ArrayDeque<TaskExecutor> taskExecutors;
    //private final BlockingQueue<Thread> blockingQueue;
    private final ArrayDeque<Thread> queue;

    public ThreadPool(int numTaskExecutors) {
        //this.blockingQueue = pool;
        queue = new ArrayDeque<>();
        taskExecutors = new ArrayDeque<>();
        for (int i = 0; i < numTaskExecutors; ++i) {
            taskExecutors.push(new TaskExecutor(this));
        }
    }

    synchronized public Thread getTask() {
        while (queue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return queue.pop();
    }

    synchronized public void putTask(Thread a) {
        queue.push(a);
        notify();
    }


    @Override
    public void run() {
        for (TaskExecutor executor : taskExecutors) {
            executor.start();
        }
    }
}

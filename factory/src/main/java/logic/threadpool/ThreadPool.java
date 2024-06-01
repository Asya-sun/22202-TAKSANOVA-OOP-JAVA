package logic.threadpool;

import java.util.ArrayDeque;

public class ThreadPool extends Thread{
    private final ArrayDeque<TaskExecutor> taskExecutors;

    public ThreadPool(ThreadsQueue<Thread> pool, int numTaskExecutors) {
        taskExecutors = new ArrayDeque<>();
        for (int i = 0; i < numTaskExecutors; ++i) {
            taskExecutors.push(new TaskExecutor(pool));
        }
    }


    @Override
    public void run() {
        for (TaskExecutor executor : taskExecutors) {
            executor.start();
        }
    }

}

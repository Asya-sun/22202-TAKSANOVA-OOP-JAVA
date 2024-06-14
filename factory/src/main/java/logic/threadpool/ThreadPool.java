package logic.threadpool;

import java.util.ArrayDeque;

public class ThreadPool{
    private final ArrayDeque<TaskExecutor> taskExecutors;
    //private final ArrayDeque<Thread> queue;
    private final ArrayDeque<Task> tasks;

    public ThreadPool(int numTaskExecutors) {
        //queue = new ArrayDeque<>();
        tasks = new ArrayDeque<>();
        taskExecutors = new ArrayDeque<>();
        for (int i = 0; i < numTaskExecutors; ++i) {
            taskExecutors.push(new TaskExecutor(this));
        }
    }

    synchronized public Task getTask() {
        while (tasks.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return tasks.pop();
    }

    synchronized public void putTask(Task a) {
        tasks.push(a);
        notify();
    }



    public void initiate() {
        for (TaskExecutor executor : taskExecutors) {
            executor.start();
        }
    }
}

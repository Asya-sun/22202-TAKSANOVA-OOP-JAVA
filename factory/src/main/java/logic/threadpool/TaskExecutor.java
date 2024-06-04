package logic.threadpool;

public class TaskExecutor extends Thread{
    private ThreadsQueue<Thread> threadsQueue;

    public TaskExecutor(ThreadsQueue<Thread> threadsQueue1) {
        threadsQueue = threadsQueue1;
    }

    @Override
    public void run() {
        while (true) {
            threadsQueue.get().start();
        }
    }

}

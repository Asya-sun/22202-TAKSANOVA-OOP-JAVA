package logic.threadpool;

public class TaskExecutor extends Thread{
    private final ThreadPool myThreadPool;

    public TaskExecutor(ThreadPool threadPool) {
        myThreadPool = threadPool;
    }


    @Override
    public void run() {
        while (true) {
            myThreadPool.getTask().execute();
        }
    }

}

package logic.warehouses;

import logic.details.Detail;
import logic.threadpool.ThreadsQueue;

abstract public class Warehouse<Detail> extends ThreadsQueue<Detail> {
    protected int storageSize;
    protected int totalNumber;
    
    public Warehouse (int storageSize) {
        this.storageSize = storageSize;
        this.totalNumber = 0;
    }

    public boolean isFull() {
        return (queue.size() == storageSize);
    }

    @Override
    synchronized public void put(Detail a) {
        if (isFull()) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        totalNumber++;
        super.put(a);
    }


    @Override
    synchronized public Detail get() {
        notify();
        return super.get();
    }

    public int getCurrentSize() {
        return queue.size();
    }

    public int getStorageSize() {
        return storageSize;
    }

    public int getTotalNumber() {
        return totalNumber;
    }
}

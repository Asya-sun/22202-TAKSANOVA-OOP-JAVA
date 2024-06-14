package logic.warehouses;

import java.util.ArrayDeque;

abstract public class Warehouse<Detail> {
    protected int storageSize;
    protected int totalNumber;
    protected ArrayDeque<Detail> details;
    
    public Warehouse (int storageSize) {
        this.storageSize = storageSize;
        this.totalNumber = 0;
        details = new ArrayDeque<>();
    }

    public boolean isFull() {
        return (details.size() == storageSize);
    }

    synchronized public void put(Detail a) {
        //if (isFull()) {
        while (isFull()) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        totalNumber++;
        details.push(a);
        notify();
    }


    synchronized public Detail get() {
        notify();
        while (details.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return details.pop();
    }

    public int getCurrentSize() {
        return details.size();
    }

    public int getStorageSize() {
        return storageSize;
    }

    public int getTotalNumber() {
        return totalNumber;
    }
}

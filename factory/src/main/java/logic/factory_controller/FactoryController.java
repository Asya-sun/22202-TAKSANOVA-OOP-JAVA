package logic.factory_controller;

import logic.details.Accessory;
import logic.details.Auto;
import logic.details.Bodywork;
import logic.details.Engine;
import logic.threadpool.ThreadPool;
import logic.threadpool.ThreadsQueue;
import logic.warehouses.AccessoryWarehouse;
import logic.warehouses.AutoWarehouse;
import logic.warehouses.BodyworkWarehouse;
import logic.warehouses.EngineWarehouse;

public class FactoryController extends Thread{

    //warehouses
    private AccessoryWarehouse<Accessory> accessoryWarehouse;
    private final AutoWarehouse<Auto> autoWarehouse;
    private BodyworkWarehouse<Bodywork> bodyworkWarehouse;
    private EngineWarehouse<Engine> engineWarehouse;
    //tasks queue
    private ThreadsQueue<Thread> queue;
    private ThreadPool taskExecutors;

    public FactoryController(AccessoryWarehouse<Accessory> accessoryWarehouse1,
                             AutoWarehouse autoWarehouse1,
                             BodyworkWarehouse<Bodywork> bodyworkWarehouse1,
                             EngineWarehouse<Engine> engineWarehouse1,
                             int numberWorkers1) {
        accessoryWarehouse = accessoryWarehouse1;
        autoWarehouse = autoWarehouse1;
        engineWarehouse = engineWarehouse1;
        bodyworkWarehouse = bodyworkWarehouse1;
        queue = new ThreadsQueue<>();
        taskExecutors = new ThreadPool(queue, numberWorkers1);
        taskExecutors.start();
    }


    @Override
    synchronized public void run() {
        while (true) {
            try {
                wait();
                synchronized (autoWarehouse) {
                    System.out.println("autoWarehouse.getStorageSize() = " + autoWarehouse.getStorageSize());
                    if (autoWarehouse.getCurrentSize() < autoWarehouse.getStorageSize()) {
                        int prevCurSize = autoWarehouse.getCurrentSize();
                        int storSize = autoWarehouse.getStorageSize();
                        for (int i = prevCurSize; i < storSize; ++i) {
                            addTask();
                        }
                    }
                }

            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
        }
    }

    public void addTask() {
        queue.put(new Worker(accessoryWarehouse, engineWarehouse, bodyworkWarehouse, autoWarehouse));

    }

    public AutoWarehouse getAutoWarehouse() {
        return autoWarehouse;
    }

}

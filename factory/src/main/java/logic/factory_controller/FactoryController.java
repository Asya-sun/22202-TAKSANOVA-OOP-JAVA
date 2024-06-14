package logic.factory_controller;

import logic.details.Accessory;
import logic.details.Auto;
import logic.details.Bodywork;
import logic.details.Engine;
import logic.threadpool.ThreadPool;
import logic.warehouses.AccessoryWarehouse;
import logic.warehouses.AutoWarehouse;
import logic.warehouses.BodyworkWarehouse;
import logic.warehouses.EngineWarehouse;

import java.util.EventListener;

public class FactoryController {

    //warehouses
    private AccessoryWarehouse<Accessory> accessoryWarehouse;
    private final AutoWarehouse<Auto> autoWarehouse;
    private BodyworkWarehouse<Bodywork> bodyworkWarehouse;
    private EngineWarehouse<Engine> engineWarehouse;
    //tasks queue
    //private BlockingQueue<Thread> queue;
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
        //queue = new BlockingQueue<>();
        taskExecutors = new ThreadPool(numberWorkers1);
        taskExecutors.start();
    }


    public void produceNewAuto(){
        synchronized (autoWarehouse) {
            System.out.println("autoWarehouse.getStorageSize() = " + autoWarehouse.getStorageSize());
            if (autoWarehouse.getCurrentSize() < autoWarehouse.getStorageSize()) {
                int prevCurSize = autoWarehouse.getCurrentSize();
                int storSize = autoWarehouse.getStorageSize();
                for (int i = 0; i < 2 * (storSize - prevCurSize); ++i) {
                    addTask();
                }
            }
        }
    }

    public void addTask() {
        taskExecutors.putTask(new Worker(accessoryWarehouse, engineWarehouse, bodyworkWarehouse, autoWarehouse));

    }
}

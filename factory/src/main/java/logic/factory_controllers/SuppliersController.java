package logic.factory_controllers;

import logic.details.*;
import logic.threadpool.TaskExecutor;
import logic.threadpool.ThreadPool;
import logic.threadpool.ThreadsQueue;
import logic.warehouses.AccessoryWarehouse;
import logic.warehouses.AutoWarehouse;
import logic.warehouses.BodyworkWarehouse;
import logic.warehouses.EngineWarehouse;

public class SuppliersController {
    //warehouses
    private AccessoryWarehouse<Accessory> accessoryWarehouse;
    private EngineWarehouse<Engine> engineWarehouse;
    private BodyworkWarehouse<Bodywork> bodyworkWarehouse;
    private AutoWarehouse<Auto> autoWarehouse;
    //private int numberWorkers;
    private ThreadsQueue<Thread> queue;
    private ThreadPool taskExecutors;

    public SuppliersController(int numberWorkers1,
                               AccessoryWarehouse<Accessory> accessoryWarehouse1,
                               EngineWarehouse<Engine> engineWarehouse1,
                               BodyworkWarehouse<Bodywork> bodyworkWarehouse1,
                               AutoWarehouse<Auto> autoWarehouse1) {
        //numberWorkers = numberWorkers1;
        accessoryWarehouse = accessoryWarehouse1;
        engineWarehouse = engineWarehouse1;
        bodyworkWarehouse = bodyworkWarehouse1;
        autoWarehouse = autoWarehouse1;
        queue = new ThreadsQueue<>();
        taskExecutors = new ThreadPool(queue, numberWorkers1);
        taskExecutors.start();

    }

    public void addTask() {
        queue.put(new Worker(accessoryWarehouse, engineWarehouse, bodyworkWarehouse, autoWarehouse));

    }


}

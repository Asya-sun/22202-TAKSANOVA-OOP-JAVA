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

public class FactoryController {

    //warehouses
    private AccessoryWarehouse<Accessory> accessoryWarehouse;
    private final AutoWarehouse<Auto> autoWarehouse;
    private BodyworkWarehouse<Bodywork> bodyworkWarehouse;
    private EngineWarehouse<Engine> engineWarehouse;
    //tasks queue
    //private BlockingQueue<Thread> queue;
    private ThreadPool workers;

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
        workers = new ThreadPool(numberWorkers1);
        workers.initiate();
    }


    public void produceNewAuto(){
        synchronized (autoWarehouse) {
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
        workers.putTask(new WorkerTask(accessoryWarehouse, engineWarehouse, bodyworkWarehouse, autoWarehouse));

    }
}

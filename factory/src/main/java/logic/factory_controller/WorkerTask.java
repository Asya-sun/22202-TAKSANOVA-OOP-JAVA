package logic.factory_controller;

import logic.details.*;
import logic.threadpool.Task;
import logic.warehouses.*;

import static java.lang.Thread.sleep;

public class WorkerTask extends Task {
    private int period;
    AccessoryWarehouse<Accessory> accessoryWarehouse;
    EngineWarehouse<Engine> engineWarehouse;
    BodyworkWarehouse<Bodywork> bodyworkWarehouse;
    AutoWarehouse<Auto> autoWarehouse;


    public WorkerTask(int period1,
                      AccessoryWarehouse<Accessory> accessoryWarehouse1,
                      EngineWarehouse<Engine> engineWarehouse1,
                      BodyworkWarehouse<Bodywork> bodyworkWarehouse1,
                      AutoWarehouse<Auto> autoWarehouse1) {
        period = period1;
        engineWarehouse = engineWarehouse1;
        bodyworkWarehouse = bodyworkWarehouse1;
        accessoryWarehouse = accessoryWarehouse1;
        autoWarehouse = autoWarehouse1;
    }

    public WorkerTask(AccessoryWarehouse<Accessory> accessoryWarehouse1,
                      EngineWarehouse<Engine> engineWarehouse1,
                      BodyworkWarehouse<Bodywork> bodyworkWarehouse1,
                      AutoWarehouse<Auto> autoWarehouse1) {
        period = 1000;
        engineWarehouse = engineWarehouse1;
        bodyworkWarehouse = bodyworkWarehouse1;
        accessoryWarehouse = accessoryWarehouse1;
        autoWarehouse = autoWarehouse1;
    }


     @Override
    public void execute() {
         try {
             sleep(period);
         } catch (InterruptedException e) {
             throw new RuntimeException(e);
         }

         Bodywork bodywork = bodyworkWarehouse.get();
         Engine engine = engineWarehouse.get();
         Accessory accessory = accessoryWarehouse.get();

         Auto auto = new Auto(engine, bodywork, accessory);

         autoWarehouse.put(auto);

     }

}

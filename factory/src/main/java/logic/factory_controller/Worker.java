package logic.factory_controller;

import logic.details.*;
import logic.warehouses.*;

public class Worker extends Thread{
    private int period;
    AccessoryWarehouse<Accessory> accessoryWarehouse;
    EngineWarehouse<Engine> engineWarehouse;
    BodyworkWarehouse<Bodywork> bodyworkWarehouse;
    AutoWarehouse<Auto> autoWarehouse;


    public Worker(int period1,
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

    public Worker(AccessoryWarehouse<Accessory> accessoryWarehouse1,
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
    public void run() {
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

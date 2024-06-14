package logic.suppliers;

import logic.details.Detail;
import logic.details.Engine;
import logic.id_work.CreatorID;
import logic.warehouses.EngineWarehouse;


public class EngineSupplier extends Supplier {
    public EngineSupplier(int period1, EngineWarehouse<Detail> warehouse1) {
        super(period1, warehouse1);
    }

    @Override
    void supply() throws InterruptedException {
        sleep(period);
        warehouse.put(new Engine(CreatorID.getID()));
    }
}

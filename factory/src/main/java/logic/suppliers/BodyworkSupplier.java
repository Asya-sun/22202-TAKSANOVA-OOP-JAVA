package logic.suppliers;

import logic.details.Bodywork;
import logic.details.Detail;
import logic.id_work.CreatorID;
import logic.warehouses.BodyworkWarehouse;

public class BodyworkSupplier extends Supplier {
    public BodyworkSupplier(int period1, BodyworkWarehouse<Detail> warehouse1) {
        super(period1, warehouse1);
    }

    @Override
    void supply() throws InterruptedException {
        sleep(period);
        warehouse.put(new Bodywork(CreatorID.getID()));
    }
}

package logic.suppliers;

import logic.details.Accessory;
import logic.details.Detail;
import logic.id_work.CreatorID;
import logic.warehouses.AccessoryWarehouse;

public class AccessorySupplier extends Supplier {
    public AccessorySupplier(int period1, AccessoryWarehouse<Detail> warehouse1) {
        super(period1, warehouse1);
    }

    @Override
    void supply() throws InterruptedException {
        sleep(period);
        warehouse.put(new Accessory(CreatorID.getID()));
    }
}

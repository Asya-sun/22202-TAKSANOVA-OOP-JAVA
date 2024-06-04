package logic.warehouses;

import logic.details.Auto;
import logic.factory_controllers.AutoWarehouseController;

public class AutoWarehouse<Auto> extends Warehouse<Auto>{

    public AutoWarehouse(int storageSize) {
        super(storageSize);
    }

    /*
    @Override
    synchronized public Detail get() {
        notify();
        return super.get();
    }
     */
}

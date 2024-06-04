package logic.warehouses;

import logic.details.Auto;
import logic.factory_controllers.AutoWarehouseController;
import logic.observer_pattern.Observable;

public class AutoWarehouse<Auto> extends Warehouse<Auto> {

    public AutoWarehouse(int storageSize) {
        super(storageSize);
    }

}

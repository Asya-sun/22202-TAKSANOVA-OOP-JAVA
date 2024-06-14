package logic.warehouses;

import logic.details.Accessory;

public class AccessoryWarehouse<Accessory> extends Warehouse<Accessory>{
    public AccessoryWarehouse(int storageSize) {
        super(storageSize);
    }
}

package logic.warehouses;

import logic.details.Engine;

public class EngineWarehouse<Engine> extends Warehouse<Engine>{
    public EngineWarehouse(int storageSize) {
        super(storageSize);
    }
}

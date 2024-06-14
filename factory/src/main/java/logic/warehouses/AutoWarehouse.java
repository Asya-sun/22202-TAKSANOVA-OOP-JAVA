package logic.warehouses;

import logic.factory_controller.FactoryController;

public class AutoWarehouse<Auto> extends Warehouse<Auto> {
    private FactoryController factoryController;

    public AutoWarehouse(int storageSize) {
        super(storageSize);
    }

    public void setFactoryController(FactoryController factoryController) {
        this.factoryController = factoryController;
    }


    @Override
    synchronized public Auto get() {
        if (factoryController == null) {
            throw new RuntimeException("factory controller os not defined\ndefine it");
        } else {
            factoryController.produceNewAuto();
        }

        return (Auto) super.get();
    }

}

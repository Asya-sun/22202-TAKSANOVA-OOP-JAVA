package logic.factory_controllers;

import logic.details.Auto;
import logic.warehouses.AutoWarehouse;

public class AutoWarehouseController extends Thread{
    private final AutoWarehouse<Auto> autoWarehouse;
    private final SuppliersController suppliersController;

    public AutoWarehouseController(AutoWarehouse autoWarehouse1, SuppliersController suppliersController1) {
        this.autoWarehouse = autoWarehouse1;
        suppliersController = suppliersController1;
    }


    @Override
    synchronized public void run() {
        while (true) {
            try {
                wait();
                synchronized (autoWarehouse) {
                    if (autoWarehouse.getCurrentSize() < autoWarehouse.getStorageSize()) {
                        int prevCurSize = autoWarehouse.getCurrentSize();
                        int storSize = autoWarehouse.getStorageSize();
                        for (int i = prevCurSize; i < storSize; ++i) {
                            suppliersController.addTask();
                        }
                    }
                }

            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
        }
    }

}

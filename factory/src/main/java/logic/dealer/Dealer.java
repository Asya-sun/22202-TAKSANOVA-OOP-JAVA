package logic.dealer;

import logic.details.Auto;
import logic.details.Detail;
import logic.factory_controllers.AutoWarehouseController;
import logic.warehouses.AutoWarehouse;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class Dealer extends Thread{
    private int period;
    protected AutoWarehouse<Auto> warehouse;
    protected final AutoWarehouseController autoWarehouseController;
    private int dealerNumber;
    private final Logger logger = LoggerFactory.getLogger(Dealer.class);
    private boolean logUse;
    //хранилище
    public Dealer(int period1,
                  AutoWarehouse<Auto> warehouse1,
                  AutoWarehouseController autoWarehouseController,
                  boolean logUse,
                  int dealerNumber) {
        this.logUse = logUse;
        this.dealerNumber = dealerNumber;
        if (period1 < 0) {
            throw new RuntimeException();
        }
        this.period = period1;
        this.warehouse = warehouse1;
        this.autoWarehouseController = autoWarehouseController;

    }


    public Dealer(int period1, AutoWarehouse<Auto> warehouse1, AutoWarehouseController autoWarehouseController, int dealerNumber) {
        this(period1, warehouse1, autoWarehouseController, false, dealerNumber);
    }


    void buy()  throws InterruptedException {
        sleep(period);
        synchronized (autoWarehouseController) {
            autoWarehouseController.notify();
        }
        //Auto auto = warehouse.get();
        Auto auto = (Auto) autoWarehouseController.getAutoWarehouse().get();
        if (logUse) {
            logger.info("Dealer {0} : Auto {1} : (Bodywork: {2}, Engine {3}, Accessory {4})",
                    dealerNumber, auto.getId(), auto.getBodywork().getId(), auto.getEngine().getId(), auto.getAccessory().getId());
        }
    }


    @Override
    public void run() {
        try {
            buy();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void setPeriod(int period) {
        this.period = period;
    }
}

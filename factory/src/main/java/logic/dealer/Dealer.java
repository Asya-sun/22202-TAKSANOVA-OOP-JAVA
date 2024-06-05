package logic.dealer;

import logic.details.Auto;
import logic.factory_controller.FactoryController;
import logic.warehouses.AutoWarehouse;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class Dealer extends Thread{
    private int period;
    protected AutoWarehouse<Auto> warehouse;
    protected final FactoryController factoryController;
    private int dealerNumber;
    private final Logger logger = LoggerFactory.getLogger(Dealer.class);
    private boolean logUse;
    //хранилище
    public Dealer(int period1,
                  AutoWarehouse<Auto> warehouse1,
                  FactoryController factoryController,
                  boolean logUse,
                  int dealerNumber) {
        this.logUse = logUse;
        this.dealerNumber = dealerNumber;
        if (period1 < 0) {
            throw new RuntimeException();
        }
        this.period = period1;
        this.warehouse = warehouse1;
        this.factoryController = factoryController;

    }


    public Dealer(int period1, AutoWarehouse<Auto> warehouse1, FactoryController factoryController, int dealerNumber) {
        this(period1, warehouse1, factoryController, false, dealerNumber);
    }


    void buy()  throws InterruptedException {
        sleep(period);
        synchronized (factoryController) {
            factoryController.notify();
        }
        //Auto auto = warehouse.get();
        Auto auto = (Auto) factoryController.getAutoWarehouse().get();
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

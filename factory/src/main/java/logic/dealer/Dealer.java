package logic.dealer;

import logic.details.Auto;
import logic.details.Detail;
import logic.warehouses.AutoWarehouse;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class Dealer extends Thread{
    private int period;
    protected AutoWarehouse<Auto> warehouse;
    private int dealerNumber;
    private final Logger logger = LoggerFactory.getLogger(Dealer.class);
    private boolean logUse;
    //хранилище
    public Dealer(int period1, AutoWarehouse<Auto> warehouse1, boolean logUse, int dealerNumber) {
        this.logUse = logUse;
        this.dealerNumber = dealerNumber;
        if (period1 < 0) {
            throw new RuntimeException();
        }
        this.period = period1;
        this.warehouse = warehouse1;

    }


    public Dealer(int period1, AutoWarehouse<Auto> warehouse1, int dealerNumber) {
        this(period1, warehouse1, false, dealerNumber);
    }

    void buy() throws InterruptedException {
        while (true) {
            sleep(period);
            Auto auto = warehouse.get();
            if (logUse) {
                logger.info("Dealer {0} : Auto {1} : (Bodywork: {2}, Engine {3}, Accessory {4})",
                        dealerNumber, auto.getId(), auto.getBodywork().getId(), auto.getEngine().getId(), auto.getAccessory().getId());
            }
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

package logic.suppliers;

import logic.warehouses.Warehouse;
import logic.details.Detail;

abstract public class Supplier extends Thread {
    protected int period;
    protected Warehouse<Detail> warehouse;

    public Supplier(int period1, Warehouse<Detail> warehouse1) {
        if (period1 < 0) {
            throw new RuntimeException();
        }
        this.period = period1;
        this.warehouse = warehouse1;

    }

    abstract void supply() throws InterruptedException;

    @Override
    public void run() {
        while (true) {
            try {
                supply();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public void setPeriod(int period) {
        this.period = period;
    }
}

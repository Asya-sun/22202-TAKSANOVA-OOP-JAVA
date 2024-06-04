package logic;

import logic.observer_pattern.Event;
import logic.observer_pattern.*;

public class Parameters extends Observable implements Observer  {
    public int accessoryPeriod;
    public int bodyworkPeriod;
    public int dealerPeriod;
    public int enginePeriod;

    public Parameters() {
        this(5000, 5000, 5000, 5000);
    }


    public Parameters(int accessoryPeriod, int bodyworkPeriod, int dealerPeriod, int enginePeriod) {
        this.accessoryPeriod = accessoryPeriod;
        this.dealerPeriod = dealerPeriod;
        this.bodyworkPeriod = bodyworkPeriod;
        this.enginePeriod = enginePeriod;
    }

    /*
    @Override
    public void update(Event event) {

    }

     */
}

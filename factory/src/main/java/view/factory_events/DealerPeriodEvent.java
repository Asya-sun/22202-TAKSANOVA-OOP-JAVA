package view.factory_events;

import logic.observer_pattern.Event;

public class DealerPeriodEvent extends Event {
    public DealerPeriodEvent(int value) {
        super(value);
    }
}

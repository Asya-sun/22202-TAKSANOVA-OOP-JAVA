package view.factory_events;

import logic.observer_pattern.Event;

public class AccessoryPeriodEvent extends Event {
    public AccessoryPeriodEvent(int value) {
        super(value);
    }
}

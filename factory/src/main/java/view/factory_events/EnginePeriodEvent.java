package view.factory_events;

import logic.observer_pattern.Event;

public class EnginePeriodEvent extends Event {
    public EnginePeriodEvent(int value) {
        super(value);
    }
}

package view.factory_observables;

import logic.observer_pattern.Observable;
import view.factory_events.AccessoryPeriodEvent;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class AccessoryPeriodObservable extends Observable implements ChangeListener {

    @Override
    public void stateChanged(ChangeEvent e) {
        notify(new AccessoryPeriodEvent(((JSlider)e.getSource()).getValue()));
    }
}

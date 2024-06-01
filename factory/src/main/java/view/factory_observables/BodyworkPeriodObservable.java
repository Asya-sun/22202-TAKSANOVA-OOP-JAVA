package view.factory_observables;

import logic.observer_pattern.Observable;
import view.factory_events.BodyworkPeriodEvent;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class BodyworkPeriodObservable extends Observable implements ChangeListener {

    @Override
    public void stateChanged(ChangeEvent e) {
        notify(new BodyworkPeriodEvent(((JSlider)e.getSource()).getValue()));
    }
}

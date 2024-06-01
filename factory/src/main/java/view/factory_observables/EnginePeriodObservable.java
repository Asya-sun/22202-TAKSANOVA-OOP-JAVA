package view.factory_observables;

import logic.observer_pattern.Observable;
import view.factory_events.EnginePeriodEvent;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class EnginePeriodObservable extends Observable implements ChangeListener {

    @Override
    public void stateChanged(ChangeEvent e) {
        notify(new EnginePeriodEvent(((JSlider)e.getSource()).getValue()));
    }
}

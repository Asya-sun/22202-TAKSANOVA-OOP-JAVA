package logic.observer_pattern;

import java.util.ArrayList;

abstract public class Observable {
    private final ArrayList<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }


    public void notify(Event event){
        for (Observer a : observers) {
            a.notify(event);
        }

    }
}

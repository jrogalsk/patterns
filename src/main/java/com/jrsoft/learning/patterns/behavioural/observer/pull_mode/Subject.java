package com.jrsoft.learning.patterns.behavioural.observer.pull_mode;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    private List<Observer> observers = new ArrayList<>();

    public void register(Observer o) {
        observers.add(o);
    }

    public void notifyObservers() {
        for (Observer o : observers)
            o.update();
    }

    public void remove(Observer o) {
        observers.remove(o);
    }

    public void clear() {
        observers.clear();
    }
}

package com.jrsoft.learning.patterns.behavioural.observer.push_mode;

import java.util.ArrayList;
import java.util.List;

public class Subject<T> {
    private List<Observer<T>> observers = new ArrayList<>();

    public void register(Observer<T> o) {
        observers.add(o);
    }

    public void notifyObservers(T pushedData) {
        for (Observer o : observers)
            o.update(pushedData);
    }

    public void remove(Observer<T> o) {
        observers.remove(o);
    }

    public void clear() {
        observers.clear();
    }
}

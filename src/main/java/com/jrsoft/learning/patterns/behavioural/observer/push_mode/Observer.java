package com.jrsoft.learning.patterns.behavioural.observer.push_mode;

public interface Observer<T> {
    void update(T pushedData);
}

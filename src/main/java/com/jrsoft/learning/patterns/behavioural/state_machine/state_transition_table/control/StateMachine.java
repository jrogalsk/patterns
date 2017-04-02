package com.jrsoft.learning.patterns.behavioural.state_machine.state_transition_table.control;

import com.jrsoft.learning.patterns.behavioural.state_machine.state_transition_table.entity.AbstractEvent;
import com.jrsoft.learning.patterns.behavioural.state_machine.state_transition_table.entity.Event;
import com.jrsoft.learning.patterns.behavioural.state_machine.state_transition_table.entity.State;
import com.jrsoft.learning.patterns.behavioural.state_machine.state_transition_table.entity.StateNotFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class StateMachine {

    private static String DEFAULT_NAME = "Unnamed";

    private String name;
    private State start;
    private List<Event> resetEvents = new ArrayList<>();

    public StateMachine(final State start) {
        this(DEFAULT_NAME, start);
    }

    public StateMachine(final String name, final State start) {
        this.name = name;
        this.start = start;
    }

    public Collection<State> getStates() {
        List<State> results = new ArrayList<>();
        collectStates(results, start);

        return results;
    }

    public void addResetEvents(Event... events) {
        Collections.addAll(this.resetEvents, events);
    }

    private void addResetEventByAddingTransition(Event e) {
        this.getStates().stream()
            .filter(s -> !s.hasTransition(e.getCode()))
            .forEach(s -> s.addTransition(e, start));
    }

    private void collectStates(final List<State> results, final State state) {
        if (results.contains(state)) return;
        results.add(state);
        for (State next : state.getAllTargets())
            this.collectStates(results, next);
    }

    public boolean isResetEvent(final String eventCode) {
        return this.resetEventCodes().contains(eventCode);
    }

    private List<String> resetEventCodes() {
        return this.resetEvents.stream()
            .map(AbstractEvent::getCode)
            .collect(Collectors.toList());
    }

    public State getStart() {
        return start;
    }

    public State getState(final String stateName) {
        return this.getStates().stream()
            .filter(s -> s.getName().equals(stateName))
            .findFirst()
            .orElseThrow(() -> new StateNotFoundException(this, stateName));
    }

    public String getName() {
        return this.name;
    }
}

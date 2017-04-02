package com.jrsoft.learning.patterns.behavioural.state_machine.state_transition_table.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class State {

    private String name;
    private List<Command> actions = new ArrayList<>();
    private Map<String, Transition> transitions = new LinkedHashMap<>();

    public State(final String name) {
        this.name = name;
    }

    public void addTransition(Event event, State targetState) {
        this.transitions.put(event.getCode(), new Transition(this, event, targetState));
    }

    public Collection<State> getAllTargets() {
        return this.transitions.values().stream()
            .map(Transition::getTarget)
            .collect(Collectors.toList());
    }

    public boolean hasTransition(final String code) {
        return this.transitions.containsKey(code);
    }

    public State targetState(String eventCode) {
        return transitions.get(eventCode).getTarget();
    }

    public void executeActions(CommandChannel commandsChannel) {
        for (Command c : actions) commandsChannel.send(c.getCode());
    }

    public void addAction(final Command command) {
        actions.add(command);
    }

    public String getName() {
        return this.name;
    }
}

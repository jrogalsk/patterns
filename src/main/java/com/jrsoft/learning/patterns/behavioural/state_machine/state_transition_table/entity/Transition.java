package com.jrsoft.learning.patterns.behavioural.state_machine.state_transition_table.entity;

public class Transition {
    private final State source;
    private final Event trigger;
    private final State target;

    public Transition(final State source, final Event trigger, final State target) {
        this.source = source;
        this.trigger = trigger;
        this.target = target;
    }

    public State getSource() {
        return this.source;
    }

    public Event getTrigger() {
        return this.trigger;
    }

    public State getTarget() {
        return this.target;
    }

    public String getTriggerCode() {
        return trigger.getCode();
    }
}

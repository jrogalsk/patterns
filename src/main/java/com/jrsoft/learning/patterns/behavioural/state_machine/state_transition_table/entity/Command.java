package com.jrsoft.learning.patterns.behavioural.state_machine.state_transition_table.entity;

public class Command extends AbstractEvent {
    public Command(final String name, final String code) {
        super(name, code);
    }
}

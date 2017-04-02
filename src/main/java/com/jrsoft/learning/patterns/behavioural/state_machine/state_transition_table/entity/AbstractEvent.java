package com.jrsoft.learning.patterns.behavioural.state_machine.state_transition_table.entity;

public class AbstractEvent {

    private String name;
    private String code;

    public AbstractEvent(final String name, final String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public String getCode() {
        return this.code;
    }

}

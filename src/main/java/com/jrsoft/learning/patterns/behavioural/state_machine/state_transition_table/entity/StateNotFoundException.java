package com.jrsoft.learning.patterns.behavioural.state_machine.state_transition_table.entity;


import com.jrsoft.learning.patterns.behavioural.state_machine.state_transition_table.control.StateMachine;

public class StateNotFoundException extends RuntimeException {
    public StateNotFoundException(final StateMachine stateMachine, final String stateName) {
        super(String.format("State '%s' not found in '%s' state machine", stateName, stateMachine.getName()));
    }
}

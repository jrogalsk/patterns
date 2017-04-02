package com.jrsoft.learning.patterns.behavioural.state_machine.state_transition_table.control;

import com.jrsoft.learning.patterns.behavioural.state_machine.state_transition_table.entity.CommandChannel;
import com.jrsoft.learning.patterns.behavioural.state_machine.state_transition_table.entity.State;

public class Controller {
    private State currentState;
    private StateMachine machine;
    private CommandChannel commandsChannel;

    public Controller(final State startState, final StateMachine machine, final CommandChannel commandsChanel) {
        this.currentState = startState;
        this.machine = machine;
        this.commandsChannel = commandsChanel;
    }

    public Controller(final StateMachine machine, final CommandChannel commandChannel) {
        this.currentState = machine.getStart();
        this.machine = machine;
        this.commandsChannel = commandChannel;
    }

    public CommandChannel getCommandChannel() {
        return commandsChannel;
    }

    public void handle(final String eventCode) {
        if (this.currentState.hasTransition(eventCode)) {
            this.transitionTo(this.currentState.targetState(eventCode));
        } else if (this.machine.isResetEvent(eventCode)) {
            this.transitionTo(this.machine.getStart());
        }
        // Ignore other event codes
    }

    private void transitionTo(final State target) {
        this.currentState = target;
        this.currentState.executeActions(this.commandsChannel);
    }

    public State getCurrentState() {
        return currentState;
    }
}

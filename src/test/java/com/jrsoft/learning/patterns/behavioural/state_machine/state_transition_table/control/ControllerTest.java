package com.jrsoft.learning.patterns.behavioural.state_machine.state_transition_table.control;

import com.jrsoft.learning.patterns.behavioural.state_machine.state_transition_table.entity.CommandChannel;
import com.jrsoft.learning.patterns.behavioural.state_machine.state_transition_table.entity.Event;
import com.jrsoft.learning.patterns.behavioural.state_machine.state_transition_table.entity.State;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ControllerTest {

    @Test
    public void eventCausesTransition() {
        State idle = new State("idle");
        StateMachine machine = new StateMachine(idle);
        Event cause = new Event("cause", "EV01");
        State target = new State("target");
        idle.addTransition(cause, target);
        Controller controller = new Controller(machine, new CommandChannel());
        controller.handle("EV01");

        assertEquals(target, controller.getCurrentState());
    }



}

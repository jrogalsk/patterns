package com.jrsoft.learning.patterns.behavioural.state_machine.state_transition_table.control;

import com.jrsoft.learning.patterns.behavioural.state_machine.state_transition_table.entity.Event;
import com.jrsoft.learning.patterns.behavioural.state_machine.state_transition_table.entity.State;
import org.junit.Test;

public class StateMachineLoaderTest {

    @Test
    public void loadsStatesWithTransitions() {

        State idle = new State("idle");
        State target = new State("target");
        Event trigger = new Event("trigger", "TGGR");
        idle.addTransition(trigger, target);
        StateMachine expected = new StateMachine(idle);

        String code =
            "events trigger TGGR end " +
                "state idle " +
                "trigger => target " +
                "end " +
                "state target end ";
        StateMachine actual = StateMachineLoader.loadString(code);

        assertEquivalentMachines(expected, actual);

    }

    private void assertEquivalentMachines(final StateMachine expected, final StateMachine actual) {
        // TODO: Implement. You can use 'Notification (193)' pattern which will return where the equality is not met
    }

}

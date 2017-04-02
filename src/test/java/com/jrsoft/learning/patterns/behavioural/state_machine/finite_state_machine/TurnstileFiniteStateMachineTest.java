package com.jrsoft.learning.patterns.behavioural.state_machine.finite_state_machine;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TurnstileFiniteStateMachineTest extends TurnstileFiniteStateMachine {
    private TurnstileFiniteStateMachine fsm;
    private String actions;

    @Before
    public void setUp() {
        this.fsm = this;
        this.fsm.setState(OneCoinTurnstileState.LOCKED);
        this.actions = "";
    }

    private void assertActions(String expected) {
        assertEquals(expected, actions);
    }

    @Test
    public void normalOperation() throws Exception {
        this.coin();
        assertActions("U");
        this.pass();
        assertActions("UL");
    }

    @Test
    public void manyCoinsThanPass() {
        for (int i = 0; i < 5; i++)
            this.coin();
        this.pass();
        assertActions("UTTTTL");
    }

    @Override
    public void lock() {
        this.actions += "L";
    }

    @Override
    public void unlock() {
        this.actions += "U";
    }

    @Override
    public void thankyou() {
        this.actions += "T";
    }

    @Override
    public void alarm() {
        this.actions += "U";
    }
}

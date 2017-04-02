package com.jrsoft.learning.patterns.behavioural.state_machine.finite_state_machine;

public enum OneCoinTurnstileState implements TurnstileState {

    LOCKED {
        @Override
        public void pass(TurnstileFiniteStateMachine fsm) {
            fsm.alarm();
        }

        @Override
        public void coin(TurnstileFiniteStateMachine fsm) {
            fsm.setState(UNLOCKED);
            fsm.unlock();
        }
    },

    UNLOCKED {
        @Override
        public void pass(TurnstileFiniteStateMachine fsm) {
            fsm.setState(LOCKED);
            fsm.lock();
        }

        @Override
        public void coin(TurnstileFiniteStateMachine fsm) {
           fsm.thankyou();
        }
    }

}

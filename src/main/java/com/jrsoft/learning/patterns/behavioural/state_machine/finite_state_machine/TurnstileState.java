package com.jrsoft.learning.patterns.behavioural.state_machine.finite_state_machine;

public interface TurnstileState {
    void pass(TurnstileFiniteStateMachine turnstileFiniteStateMachine);

    void coin(TurnstileFiniteStateMachine turnstileFiniteStateMachine);
}

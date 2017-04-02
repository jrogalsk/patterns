package com.jrsoft.learning.patterns.behavioural.state_machine.finite_state_machine;

public abstract class TurnstileFiniteStateMachine {
    private TurnstileState state;

    public void pass() {
        this.state.pass(this);
    }

    public void coin() {
        this.state.coin(this);
    }

    public void setState(TurnstileState state) {
        this.state = state;
    }

    public abstract void lock();

    public abstract void unlock();

    public abstract void thankyou();

    public abstract void alarm();
}

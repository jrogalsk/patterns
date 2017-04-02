package com.jrsoft.learning.patterns.behavioural.state_machine.state_transition_table.boundary;

import com.jrsoft.learning.patterns.behavioural.state_machine.state_transition_table.control.Controller;
import com.jrsoft.learning.patterns.behavioural.state_machine.state_transition_table.control.StateMachine;
import com.jrsoft.learning.patterns.behavioural.state_machine.state_transition_table.entity.Command;
import com.jrsoft.learning.patterns.behavioural.state_machine.state_transition_table.entity.CommandChannel;
import com.jrsoft.learning.patterns.behavioural.state_machine.state_transition_table.entity.Event;
import com.jrsoft.learning.patterns.behavioural.state_machine.state_transition_table.entity.State;

public class ControllerResource {

    public Controller getMissGrantsController() {
        Event doorClosed = new Event("doorClosed", "D1CL");
        Event drawerOpened = new Event("drawerOpened", "D2OP");
        Event lightOn = new Event("lightOn", "L1ON");
        Event doorOpened = new Event("doorOpened", "D1OP");
        Event panelClosed = new Event("panelClosed", "PNCL");

        Command unlockPanelCmd = new Command("unlockPanel", "PNUL");
        Command lockPanelCmd = new Command("lockPanel", "PNLK");
        Command lockDoorCmd = new Command("lockDoor", "D1LK");
        Command unlockDoorCmd = new Command("unlockDoor", "D1UL");


        State idle = new State("idle");
        State activeState = new State("active");
        State waitingForLightState = new State("waitingForLight");
        State waitingForDrawerState = new State("waitingForDrawer");
        State unlockPanelState = new State("unlockPanel");

        StateMachine stateMachine = new StateMachine(idle);

        idle.addTransition(doorClosed, activeState);
        idle.addAction(unlockDoorCmd);
        idle.addAction(lockPanelCmd);

        activeState.addTransition(drawerOpened, waitingForDrawerState);
        activeState.addTransition(lightOn, waitingForDrawerState);

        waitingForLightState.addTransition(lightOn, unlockPanelState);

        waitingForDrawerState.addTransition(drawerOpened, unlockPanelState);

        unlockPanelState.addAction(unlockDoorCmd);
        unlockPanelState.addAction(lockDoorCmd);
        unlockPanelState.addTransition(panelClosed, idle);

        stateMachine.addResetEvents(doorOpened);


        return new Controller(idle, stateMachine, new CommandChannel());
    }

}

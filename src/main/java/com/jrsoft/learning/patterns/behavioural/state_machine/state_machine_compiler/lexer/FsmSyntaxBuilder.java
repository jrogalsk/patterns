package com.jrsoft.learning.patterns.behavioural.state_machine.state_machine_compiler.lexer;

import com.jrsoft.learning.patterns.behavioural.state_machine.state_machine_compiler.lexer.FsmSyntax.Header;
import com.jrsoft.learning.patterns.behavioural.state_machine.state_machine_compiler.lexer.FsmSyntax.Transition;

import static com.jrsoft.learning.patterns.behavioural.state_machine.state_machine_compiler.lexer.FsmSyntax.StateSpec;
import static com.jrsoft.learning.patterns.behavioural.state_machine.state_machine_compiler.lexer.FsmSyntax.SubTransition;

public class FsmSyntaxBuilder implements Builder {
    private FsmSyntax fsm;
    private Header header;
    private String parsedName;
    private Transition transition;
    private SubTransition subTransition;

    public FsmSyntaxBuilder() {
        fsm = new FsmSyntax();
    }

    @Override
    public void newHeaderWithName() {
        header = new Header();
        header.name = parsedName;
    }

    @Override
    public void addHeaderWithValue() {
        header.value = parsedName;
        fsm.headers.add(header);
    }

    @Override
    public void setStateName() {
        transition = new Transition();
        fsm.logic.add(transition);
        transition.state = new StateSpec();
        transition.state.name = parsedName;
    }

    @Override
    public void done() {
        fsm.done = true;
    }

    @Override
    public void setSuperStateName() {
        setStateName();
        transition.state.abstractState = true;
    }

    @Override
    public void setEvent() {
        subTransition = new SubTransition(parsedName);
        transition.subTransitions.add(subTransition);
    }

    @Override
    public void setNullEvent() {
        subTransition = new SubTransition("null");
        transition.subTransitions.add(subTransition);
    }

    @Override
    public void setEntryAction() {
        transition.state.entryAction = parsedName;
    }

    @Override
    public void setExitAction() {
        transition.state.exitAction = parsedName;
    }

    @Override
    public void setStateBase() {
        transition.state.superState = parsedName;
    }

    @Override
    public void setNextState() {
        subTransition.nextState = parsedName;
    }

    @Override
    public void setNullNextState() {
        subTransition.nextState = "null";
    }

    @Override
    public void transitionWithAction() {
        if (subTransition.actions.isEmpty())
            subTransition.actions.add(parsedName);
    }

    @Override
    public void transitionNullAction() {
        subTransition.actions.add("{}");
    }

    @Override
    public void addAction() {
        subTransition.actions.add(parsedName);
    }

    @Override
    public void transitionWithActions() {
        throw new UnsupportedOperationException("#transitionWithActions()");
    }

    @Override
    public void headerError(ParserState parserState, ParserEvent event, int line, int pos) {
        throw new UnsupportedOperationException("#headerError()");
    }

    @Override
    public void stateSpecError(ParserState parserState, ParserEvent event, int line, int pos) {
        throw new UnsupportedOperationException("#stateSpecError()");
    }

    @Override
    public void transitionError(ParserState parserState, ParserEvent event, int line, int pos) {
        throw new UnsupportedOperationException("#transitionError()");
    }

    @Override
    public void transitionGroupError(ParserState parserState, ParserEvent event, int line, int pos) {
        throw new UnsupportedOperationException("#transitionGroupError()");
    }

    @Override
    public void endError(ParserState parserState, ParserEvent event, int line, int pos) {
        throw new UnsupportedOperationException("#endError()");
    }

    @Override
    public void syntaxError(ParserState parserState, ParserEvent event, int line, int pos) {
        throw new UnsupportedOperationException("#syntaxError()");
    }

    @Override
    public void setName(String name) {
        this.parsedName = name;
    }

    public FsmSyntax getFsm() {
        return fsm;
    }
}

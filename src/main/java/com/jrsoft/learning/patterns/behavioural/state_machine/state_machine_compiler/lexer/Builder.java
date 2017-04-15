package com.jrsoft.learning.patterns.behavioural.state_machine.state_machine_compiler.lexer;

/*
    A collection of methods which will be called by the parser.
    A lot of methods :)
    Each of these methods creates or helps to create one of the data structures in our syntax data structure.
    If called in right order it will build data structure represented in our syntax
    It's parsers job to call this functions in right order
    It's builder's job to build this structure based on that order
 */
public interface Builder {
    void newHeaderWithName();
    void addHeaderWithValue();
    void setStateName();
    void done();
    void setSuperStateName();
    void setEvent();
    void setNullEvent();
    void setEntryAction();
    void setExitAction();
    void setStateBase();
    void setNextState();
    void setNullNextState();
    void transitionWithAction();
    void transitionNullAction();
    void addAction();
    void transitionWithActions();
    void headerError(ParserState parserState, ParserEvent event, int line, int pos);
    void stateSpecError(ParserState parserState, ParserEvent event, int line, int pos);
    void transitionError(ParserState parserState, ParserEvent event, int line, int pos);
    void transitionGroupError(ParserState parserState, ParserEvent event, int line, int pos);
    void endError(ParserState parserState, ParserEvent event, int line, int pos);
    void syntaxError(ParserState parserState, ParserEvent event, int line, int pos);
    void setName(String name);
}

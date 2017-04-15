package com.jrsoft.learning.patterns.behavioural.state_machine.state_machine_compiler.lexer;

public enum ParserEvent {
    NAME,
    OPEN_BRACE,
    CLOSED_BRACE,
    OPEN_PAREN,
    CLOSED_PAREN,
    OPEN_ANGLE,
    CLOSED_ANGLE,
    DASH,
    EOF,
    COLON
}

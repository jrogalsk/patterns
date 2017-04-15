package com.jrsoft.learning.patterns.behavioural.state_machine.state_machine_compiler.lexer;

public enum ParserState {
    HEADER,
    HEADER_COLON,
    STATE_SPEC,
    HEADER_VALUE,
    SUPER_STATE_NAME,
    STATE_MODIFIER,
    END,
    SUPER_STATE_CLOSE,
    ENTRY_ACTION,
    EXIT_ACTION,
    STATE_BASE,
    SINGLE_EVENT,
    SUBTRANSITION_GROUP,
    SINGLE_NEXT_STATE,
    SINGLE_ACTION_GROUP,
    SINGLE_ACTION_GROUP_NAME,
    GROUP_EVENT,
    GROUP_NEXT_STATE,
    GROUP_ACTION_GROUP,
    GROUP_ACTION_GROUP_NAME
}

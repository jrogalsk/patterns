package com.jrsoft.learning.patterns.behavioural.state_machine.state_machine_compiler.lexer;

/*
    Contains function for each element in fsm_syntax.txt syntax
 */

public interface TokenCollector {
    void openBrace(int line, int pos);
    void closeBrace(int line, int pos);
    void openParen(int line, int pos);
    void closeParen(int line, int pos);
    void openAngle(int line, int pos);
    void closeAngle(int line, int pos);
    void dash(int line, int pos);
    void colon(int line, int pos);
    void name(String name, int line, int pos);
    void error(int line, int pos);
}

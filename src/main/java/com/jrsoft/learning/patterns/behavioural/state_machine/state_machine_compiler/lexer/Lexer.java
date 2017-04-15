package com.jrsoft.learning.patterns.behavioural.state_machine.state_machine_compiler.lexer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
    Splits file to compile to lines,
    traverses each line and detects tokens.
    Once token in line is detected, calls TokenCollector with appropriate method ex. openBrace
 */

public class Lexer {
    private TokenCollector collector;
    private int lineNumber;
    private int possition;

    public Lexer(TokenCollector collector) {
        this.collector = collector;
    }

    public void lex(String s) {
        lineNumber = 1;
        String[] lines = s.split("\n");
        for (String line : lines) {
            lexLine(line);
            lineNumber ++; // As a compiler creators, we are doing this for error messages purposes to present user where the error is
        }
    }

    private void lexLine(String line) {
        for (possition = 0; possition < line.length();) {
            lexToken(line);
        }
    }

    private void lexToken(String line) {
        if (!findToken(line)) {
            collector.error(lineNumber, possition + 1);
            possition += 1;
        }
    }

    private boolean findToken(String line) {
        return
                findWhiteSpace(line) ||
                        findSingleCharacterToken(line) ||
                        findName(line);
    }

    private static Pattern whitePattern = Pattern.compile("^\\s+");

    private boolean findWhiteSpace(String line) {
        Matcher whiteMatcher = whitePattern.matcher(line.substring(possition));
        if (whiteMatcher.find()) {
            possition += whiteMatcher.end();
            return true;
        }
        return false;
    }

    private boolean findSingleCharacterToken(String line) {
        String c = line.substring(possition, possition + 1);

        switch (c) {
            case "{":
                collector.openBrace(lineNumber, possition);
                break;
            case "}":
                collector.closeBrace(lineNumber, possition);
                break;
            case "(":
                collector.openParen(lineNumber, possition);
                break;
            case ")":
                collector.closeParen(lineNumber, possition);
                break;
            case "<":
                collector.openAngle(lineNumber, possition);
                break;
            case ">":
                collector.closeAngle(lineNumber, possition);
                break;
            case "-":
                collector.dash(lineNumber, possition);
                break;
            case ":":
                collector.colon(lineNumber, possition);
                break;
            default:
                return false;
        }
        possition++;
        return true;
    }

    private static Pattern namePattern = Pattern.compile("^\\w+");

    private boolean findName(String line) {
        Matcher nameMatcher = namePattern.matcher(line.substring(possition));
        if (nameMatcher.find()) {
            collector.name(nameMatcher.group(0), lineNumber, possition);
            possition += nameMatcher.end();
            return true;
        }
        return false;
    }

}

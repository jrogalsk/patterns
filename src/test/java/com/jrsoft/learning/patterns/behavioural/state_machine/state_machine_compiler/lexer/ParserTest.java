package com.jrsoft.learning.patterns.behavioural.state_machine.state_machine_compiler.lexer;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.jrsoft.learning.patterns.behavioural.state_machine.state_machine_compiler.lexer.ParserEvent.EOF;
import static org.junit.Assert.assertEquals;

@RunWith(HierarchicalContextRunner.class)
public class ParserTest {
    private Lexer lexer;
    private Parser parser;
    private FsmSyntaxBuilder builder;

    @Before
    public void setUp() {
        builder = new FsmSyntaxBuilder();
        parser = new Parser(builder);
        lexer = new Lexer(parser);
    }

    private void assertParseResult(String s, String expected) {
        lexer.lex(s);                                           // After this operation we have build data structure
        parser.handleEvent(EOF, -1, -1);                        // We finish parsing by sending eof event
        assertEquals(expected, builder.getFsm().toString());    // builder.getFsm() - fetches the actual data structure which was produced
    }

    private void assertParseError(String s, String expected) {
        lexer.lex(s);
        parser.handleEvent(EOF, -1, -1);
        assertEquals(expected, builder.getFsm().getError());
    }

    public class IncrementalTests {
        @Test
        public void parseOneHeader() {
            assertParseResult("N:V{}", "N:V\n.\n");             // . represents EOF
        }

        @Test
        public void parseManyHeaders() {
            assertParseResult("   N1:V1\tN2 : V2\n{}", "N1:V1\nN2:V2\n.\n");
        }

        @Test
        public void noHeader() {
            assertParseResult(" {}", ".\n");
        }

        @Test
        public void simpleTrasition() {
            assertParseResult("{s e ns a }",
                    "" +
                            "{\n" +
                            "  s e ns a\n" +
                            "}\n" +
                            ".\n"
            );
        }

        @Test
        public void transitionWithNullAction() {
            assertParseResult("{s e ns -}",
                    "" +
                            "{\n" +
                            "  s e ns {}\n" +
                            "}\n" +
                            ".\n"
            );

        }

        @Test
        public void transitionWithManyActions() {
            assertParseResult("{s e ns {a1 a2}}",
                    "" +
                            "{\n" +
                            "  s e ns {a1 a2}\n" +
                            "}\n" +
                            ".\n"
            );
        }

        @Test
        public void stateWithSubTransition() {
            assertParseResult("{s {e ns a}}",
                    "" +
                            "{\n" +
                            "  s e ns a\n" +
                            "}\n" +
                            ".\n"
            );
        }

        @Test
        public void stateWithSeveralSubTransitions() {
            assertParseResult("{s {e1 ns a1 e2 ns a2}}",
                    "" +
                            "{\n" +
                            "  s {\n" +
                            "    e1 ns a1\n" +
                            "    e2 ns a2\n" +
                            "  }\n" +
                            "}\n" +
                            ".\n"
            );
        }

        @Test
        public void manyTransitions() {
            assertParseResult("{s1 e1 s2 a1 s2 e2 s3 a2}",
                    "" +
                            "{\n" +
                            "  s1 e1 s2 a1\n" +
                            "  s2 e2 s3 a2\n" +
                            "}\n" +
                            ".\n"
            );
        }

        @Test
        public void superState() {
            assertParseResult("{(ss) e s a}",
                    "" +
                            "{\n" +
                            "  (ss) e s a\n" +
                            "}\n" +
                            ".\n"
            );
        }

        @Test
        public void entryAction() {
            assertParseResult("{s <ea e ns a}",
                    "" +
                            "{\n" +
                            "  s <ea e ns a\n" +
                            "}\n" +
                            ".\n"
            );
        }

        @Test
        public void exitAction() {
            assertParseResult("{s >xa e ns a}",
                    "" +
                            "{\n" +
                            "  s >xa e ns a\n" +
                            "}\n" +
                            ".\n"
            );
        }

        @Test
        public void derivedState() {
            assertParseResult("{s:ss e ns a}",
                    "" +
                            "{\n" +
                            "  s:ss e ns a\n" +
                            "}\n" +
                            ".\n");

        }

        @Test
        public void allStateAdornments() {
            assertParseResult("{(s)<ea>xa:ss e ns a}",
                    "" +
                            "{\n" +
                            "  (s):ss <ea >xa e ns a\n" +
                            "}\n" +
                            ".\n"
            );
        }

        @Test
        public void stateWithNoSubTransitions() {
            assertParseResult("{s {}}",
                    "" +
                            "{\n" +
                            "  s {\n" +
                            "  }\n" +
                            "}\n" +
                            ".\n"
            );
        }

        @Test
        public void stateWithAllDashes() {
            assertParseResult("{s - - -}",
                    "" +
                            "{\n" +
                            "  s null null {}\n" +
                            "}\n" +
                            ".\n"
            );
        }
    }

    public class AcceptanceTests {
        @Test
        public void simpleOneCoinTurnstile() {
            assertParseResult(
                    "" +
                            "Actions: Turnstile\n" +
                            "FSM: OneCoinTurnstile\n" +
                            "Initial: Locked\n" +
                            "{\n" +
                            "  Locked\tCoin\tUnlocked\t{alarmOff unlock}\n" +
                            "  Locked\tPass\tLocked\t\talarmOn\n" +
                            "  Unlocked\tCoin\tUnlocked\tthankyou\n" +
                            "  Unlocked\tPass\tLocked\t\tlock" +
                            "}",
                    "" +
                            "Actions:Turnstile\n" +
                            "FSM:OneCoinTurnstile\n" +
                            "Initial:Locked\n" +
                            "{\n" +
                            "  Locked Coin Unlocked {alarmOff unlock}\n" +
                            "  Locked Pass Locked alarmOn\n" +
                            "  Unlocked Coin Unlocked thankyou\n" +
                            "  Unlocked Pass Locked lock\n" +
                            "}\n" +
                            ".\n"
            );
        }

        @Test
        public void twoCoinTurnstileWithoutSuperState() {
            assertParseResult(
                    "" +
                            "Actions: Turnstile\n" +
                            "FSM: TwoCoinTurnstile\n" +
                            "Initial: Locked\n" +
                            "{\n" +
                            "\tLocked {\n" +
                            "\t\tPass\tAlarming\talarmOn\n" +
                            "\t\tCoin\tFirstCoin\t-\n" +
                            "\t\tReset\tLocked\t{lock alarmOff}\n" +
                            "\t}\n" +
                            "\t\n" +
                            "Alarming\tReset\tLocked {lock alarmOff}\n" +
                            "\t\n" +
                            "\tFirstCoin {\n" +
                            "\t\tPass\tAlarming\t-\n" +
                            "\t\tCoin\tUnlocked\tunlock\n" +
                            "\t\tReset\tLocked {lock alarmOff}\n" +
                            "\t}\n" +
                            "\t\n" +
                            "\tUnlocked {\n" +
                            "\t\tPass\tLocked\tlock\n" +
                            "\t\tCoin\t-\t\tthankyou\n" +
                            "\t\tReset\tLocked {lock alarmOff}\n" +
                            "\t}\n" +
                            "}",
                    "" +
                            "Actions:Turnstile\n" +
                            "FSM:TwoCoinTurnstile\n" +
                            "Initial:Locked\n" +
                            "{\n" +
                            "  Locked {\n" +
                            "    Pass Alarming alarmOn\n" +
                            "    Coin FirstCoin {}\n" +
                            "    Reset Locked {lock alarmOff}\n" +
                            "  }\n" +
                            "  Alarming Reset Locked {lock alarmOff}\n" +
                            "  FirstCoin {\n" +
                            "    Pass Alarming {}\n" +
                            "    Coin Unlocked unlock\n" +
                            "    Reset Locked {lock alarmOff}\n" +
                            "  }\n" +
                            "  Unlocked {\n" +
                            "    Pass Locked lock\n" +
                            "    Coin null thankyou\n" +
                            "    Reset Locked {lock alarmOff}\n" +
                            "  }\n" +
                            "}\n" +
                            ".\n"
            );
        }

        // TODO: Add test from 1:02:15s
    }

    public class ErrorTests {

        // TODO: implement error messages
        @Test
        public void parseNothing() {
            assertParseError("", "Syntax error: HEADER. HEADER|EOF. line -1 position -1");
        }
    }


}

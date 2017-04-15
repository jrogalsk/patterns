package com.jrsoft.learning.patterns.behavioural.state_machine.state_machine_compiler.lexer;

import com.jrsoft.learning.patterns.behavioural.state_machine.state_machine_compiler.lexer.Lexer;
import com.jrsoft.learning.patterns.behavioural.state_machine.state_machine_compiler.lexer.TokenCollector;
import de.bechte.junit.runners.context.HierarchicalContextRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(HierarchicalContextRunner.class)
public class LexerTest implements TokenCollector { // Self shunt pattern - implementing interface in test calss and using it for tests
    String tokens = "";
    Lexer lexer;
    private boolean firstToken = true;

    @Before
    public void setUp() {
        lexer = new Lexer(this);
    }

    private void addToken(String token) {
        if (!firstToken)
            tokens += ",";
        tokens += token;
        firstToken = false;
    }

    private void assertLexResult(String input, String expected) {
        lexer.lex(input);
        assertEquals(expected, tokens);
    }

    @Override
    public void openBrace(int line, int pos) {
        addToken("OB");
    }

    @Override
    public void closeBrace(int line, int pos) {
        addToken("CB");
    }

    @Override
    public void openParen(int line, int pos) {
        addToken("OP");
    }

    @Override
    public void closeParen(int line, int pos) {
        addToken("CP");
    }

    @Override
    public void openAngle(int line, int pos) {
        addToken("OA");
    }

    @Override
    public void closeAngle(int line, int pos) {
        addToken("CA");
    }

    @Override
    public void dash(int line, int pos) {
        addToken("D");
    }

    @Override
    public void colon(int line, int pos) {
        addToken("C");
    }

    @Override
    public void name(String name, int line, int pos) {
        addToken('#' + name + '#');
    }

    @Override
    public void error(int line, int pos) {
        addToken("E" + line + "/" + pos);
    }

    public class SingleTokenTests {

        @Test
        public void findsOpenBrace() {
            assertLexResult("{", "OB");
        }

        @Test
        public void findsClosesBrace() {
            assertLexResult("}", "CB");
        }

        @Test
        public void findsOpenParent() {
            assertLexResult("(", "OP");
        }

        @Test
        public void findsClosedParent() {
            assertLexResult(")", "CP");
        }

        @Test
        public void findsOpenAngle() {
            assertLexResult("<", "OA");
        }

        @Test
        public void findsCloseAngle() {
            assertLexResult(">", "CA");
        }

        @Test
        public void findsDash() {
            assertLexResult("-", "D");
        }

        @Test
        public void findsColon() {
            assertLexResult(":", "C");
        }

        @Test
        public void findsSimpleName() {
            assertLexResult("name", "#name#");
        }

        @Test
        public void findsComplexName() {
            assertLexResult("Room_222", "#Room_222#");
        }

        @Test
        public void error() {
            assertLexResult(".", "E1/1");
        }

        @Test
        public void nothingButWhitespace() {
            assertLexResult(" ", "");
        }

        @Test
        public void whiteSpaceBefore() {
            assertLexResult("  \t\n  -", "D");
        }
    }

    public class MultipleTokensTest {
        @Test
        public void simpleSequence() {
            assertLexResult("{}", "OB,CB");
        }

        @Test
        public void complexSeqence() {
            assertLexResult("FSM:fsm{this}", "#FSM#,C,#fsm#,OB,#this#,CB");
        }

        @Test
        public void allTokens() {
            assertLexResult("{}()<>-: name .", "OB,CB,OP,CP,OA,CA,D,C,#name#,E1/15");
        }

        @Test
        public void multipleLines() {
            assertLexResult("FSM:fsm. \n{bob-.}", "#FSM#,C,#fsm#,E1/8,OB,#bob#,D,E2/6,CB");
        }
    }
}

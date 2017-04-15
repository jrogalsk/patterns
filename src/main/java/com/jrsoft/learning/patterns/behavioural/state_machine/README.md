## State Machine
State machines are tricky to test because you have to test every arrow


### Finite State Machine

_Based on Uncle Bob, Episode 28_

### State Machine Compiler (SMC)

_Based on Uncle Bob, Episode 29_

#### Lexer - based on Builder pattern

Backus-Naur Form (used as our syntax) is a special case of Finite State Machine.
The stream of tokens which were found by `Lexer.java` is the stream of events that feeds finite state machine implemented in Parser.

"The finite state machine (Parser)i s driven by the tokens and it in turn calls Builder functions,
and those functions called in right order create our syntax data structure (FsmSyntax)"

`Lexer.java` - takes source code to be compiled and splits it into stream of lexical tokens.
`Parser.java` - changes stream of tokens into syntax data structure with use of builder. Parser is implemented with Finite State Machine pattern too :)
`ParserEvent.java` - definition of all tokens used by lexer
`Builder.java` - a collection of methods which will be called by the parser, responsible for building internal structure.
`SyntaxBuilder.java` - implementation of `Builder.java`
`FsmSyntax.java` - Data Structure which reflects syntax defined for Finite State Machine

####

1. We take the syntax data structure of SCM and run it through SemanticAnalyzer, validating the state machine for semantic validity.
2. We produce semantic data structure
3. Run semantic data structure through an optimizer who wil optimie the state machine by getting rid of supper states and entry actions and transform it into stream of transitions.
4. Use the visitor pattern to generate the code
    * we take the optimixed state machine and convert it in abstract tree of nodes that represent a lenguage agnostic nested switch-case statements
    * write the visitors which walks that nodes and generate code - Java, C, C++

### State Transition Table

_Based on Fowler's 'Domain Specific Language' book - Chapter 1_

* Simple approach to dynamically program controller.

## State Machine
State machines are tricky to test because you have to test every arrow


### Finite State Machine

_Based on Uncle Bob, Episode 28_

### State Machine Compiler (SMC)

_Based on Uncle Bob, Episode 29_

#### Lexer - based on Builder pattern
`Lexer.java` - changes text to be compiled into internal data structure of SMC
`FsmSyntax.java` - Data Structure which reflects syntax defined for Finite State Machine
`Parser.java` - splits text to be compiled and splits it into tokens. Parser is implemented with Finite State Machine pattern too :)
`ParserEvent.java` - definition of all tokens used by lexer
`Builder.java` - a collection of methods which will be called by the parser, responsible for building internal structure.
`SyntaxBuilder.java` - implementation of `Builder.java`

Backus-Naur Form (used as our syntax) is a special case of Finite State Machine.
The stream of tokens which were found by `Lexer.java` is the stream of events that feeds finite state machine implemented in Parser.

"The finite state machine (Parser)i s driven by the tokens and it in turn calls Builder functions,
and those functions called in right order create our syntax data structure (FsmSyntax)"

### State Transition Table

_Based on Fowler's 'Domain Specific Language' book - Chapter 1_

* Simple approach to dynamically program controller.

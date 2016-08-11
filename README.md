This is a very simple parse that works by reading a character at a time.

This project doesn't use regex, and is just for the fun of it.

EBNF of program:

expr -> term { + term }

term -> factor { * factor }

factor -> ( expr ) | decimal-number

decimal-number -> number [ . number ]

number -> digit { digit }

digit -> 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9

grammar Select;
query   : 'select' fields 'from' entity where? start? limit? order_clause? EOF;


fields: star | name (',' name)*;
star: '*';
start: 'start' INT;
limit: 'limit' INT;
order_clause: 'order by' order_name (order_name)*;
order_name: name | name 'asc' | name 'desc';
where: 'where' condition ('and' condition| 'or' condition)* ;
condition: name;
name: ANY_NAME;
entity: ANY_NAME;
INT: [0-9]+;
NUMBER:   '-'? INT '.' [0-9]+ EXP? | '-'? INT EXP | '-'? INT;
ANY_NAME: [a-zA-Z_.] [a-zA-Z._0-9]*;
WS  : [ \t\r\n]+ -> skip ;
SL_COMMENT:   '//' .*? '\n' -> skip;
fragment EXP :   [Ee] [+\-]? INT ;
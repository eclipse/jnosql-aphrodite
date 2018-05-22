grammar Select;
query   : 'select' fields 'from' entity where? start? limit? order_clause? EOF;


fields: star | name (',' name)*;
star: '*';
start: 'start' INT;
limit: 'limit' INT;
order_clause: 'order by' order_name (order_name)*;
order_name: name | name 'asc' | name 'desc';
where: 'where' condition ('and' condition| 'or' condition)* ;
condition: eq | gt | gte | lt | lte | between| in;
eq: not? name '=' number;
gt: not? name '>' number;
gte: not? name '>=' number;
lt: not? name '<' number;
lte: not? name '<=' number;
between: not? name 'between' number 'and' number;
in: name not? 'in' '('number (',' number)* ')';
not: 'not';
name: ANY_NAME;
entity: ANY_NAME;
number: '-'? INT .? INT?;
INT: [0-9]+;
ANY_NAME: [a-zA-Z_.] [a-zA-Z._0-9]*;
WS: [ \t\r\n]+ -> skip ;
SL_COMMENT: '//' .*? '\n' -> skip;
grammar Select;
query   : 'select' fields 'from' entity start? limit? order_clause? EOF;


fields: star | name (',' name)*;
star: '*';
order_clause: 'order by' order_name (order_name)*;
order_name: name | name 'asc' | name 'desc';
start: 'start' INT;
limit: 'limit' INT;
name: ANY_NAME;
entity: ANY_NAME;
INT: [0-9]+;
ANY_NAME: [a-zA-Z_.] [a-zA-Z._0-9]*;
WS  : [ \t\r\n]+ -> skip ;

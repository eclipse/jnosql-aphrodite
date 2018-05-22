grammar Select;
r   : 'select' fields 'from' entity start? limit? order_clause?;


fields: star | name (',' name)*;
star: '*';
order_clause: 'order by' order_name (',' order_name)*;
order_name: name | name 'asc' | name 'desc';
start: 'start' INT;
limit: 'limit' INT;
INT: [0-9]+;
name: ANY_NAME;
entity: ANY_NAME;
ANY_NAME: [a-zA-Z_.] [a-zA-Z._0-9]*;
WS  : [ \t\r\n]+ -> skip ;

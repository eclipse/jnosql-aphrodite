grammar Select;
r   : 'select' fields 'from' entity;


fields: star | name (',' name)*;
star: '*';
order: 'order by' | order_clause (' ' order_clause)*;
order_clause: name | name 'asc' | name 'desc';
name: ANY_NAME;
entity: ANY_NAME;
ANY_NAME: [a-zA-Z_.] [a-zA-Z._0-9]*;
WS  : [ \t\r\n]+ -> skip ;

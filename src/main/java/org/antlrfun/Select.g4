grammar Select;
r   : 'select' fields 'from' entity;


fields: star | name (',' name)*;
star: '*';
name: ANY_NAME;
entity: ANY_NAME;
ANY_NAME: [a-zA-Z_.] [a-zA-Z._0-9]*;
WS  : [ \t\r\n]+ -> skip ;

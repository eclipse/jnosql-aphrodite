grammar Method;
findBy:'findBy' variable? where? order? EOF;



where: condition (and condition| or condition)* ;
and: 'And';
or: 'Or';
condition: eq | gt | gte | lt | lte | between | in | like |
          neq | ngt | ngte | nlt | nlte | nbetween | nin | nlike;
order: 'OrderBy' orderName (orderName)*;
orderName: variable | variable asc | variable desc;
asc: 'Asc';
desc: 'Desc';
eq: variable | variable 'Equals';
neq: variable 'NotEquals';
gt: variable 'GreaterThan';
ngt: variable 'NotGreaterThan';
gte: variable 'GreaterThan';
ngte: variable 'NotGreaterThan';
lt: variable 'LessThan';
lte: variable 'LessThanEqual';
nlt: variable 'NotLessThan';
nlte: variable 'NotLessThanEqual';
between: variable 'Between';
nbetween: variable 'NotBetween';
in: variable 'In';
nin: variable 'NotIn';
like: variable 'Like';
nlike: variable 'NotLike';
variable: ANY_NAME;
ANY_NAME: [a-zA-Z_.] [a-zA-Z._0-9]*;
fragment ESC :   '\\' (["\\/bfnrt] | UNICODE) ;
fragment UNICODE : 'u' HEX HEX HEX HEX ;
fragment HEX : [0-9a-fA-F] ;
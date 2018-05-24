# Eclipse NoSQL Aphrodite

Eclipse JNoSQL Aphrodite is the project that contains the syntax query to JNoSQL API.

The general concepts about the statements:

* It ends with a break line `\n`
* It isn't ignored case
* All keywords must be in lowercase



 

## Select

The select statement reads one or more columns for one or more rows in a table. It returns a result-set of the rows matching the request, where each row contains the values for the selection corresponding to the query.

```sql
select_statement ::=  SELECT ( select_clause | '*' )
                      FROM table_name
                      [ WHERE where_clause ]
                      [ START (integer) ]
                      [ LIMIT (integer) ]
                      [ ORDER BY ordering_clause ]
```

### Sample:

```sql
select * from Person
select  name, age ,adress.age from Person order by name desc age desc
select  * from Person where birthday between "01-09-1988" and "01-09-1988" and salary = 12
select  name, age ,adress.age from Person start 20 limit 10 order by name desc age desc
```
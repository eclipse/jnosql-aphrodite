# Eclipse NoSQL Aphrodite

Eclipse JNoSQL Aphrodite is the project that contains the syntax query to JNoSQL API.

The general concepts about the statements:

* It ends with a break line `\n`
* It isn't ignored case
* All keywords must be in lowercase
* The goals are to look like SQL, however simpler
* Even with this query, a specific implementation may not support an operation, condition, so on. E.g: Column family may not support query with equals operator in a different column than the key itself.
* The goal of the API is not about forgotten the specific behavior that there is in a particular database. These features matter, that's why there's an extensible API.


## Select

The select statement reads one or more columns for one or more rows in a table. It returns a result-set of the rows matching the request, where each row contains the values for the selection corresponding to the query.

```sql
select_statement ::=  SELECT ( select_clause | '*' )
                      FROM entity_name
                      [ WHERE where_clause ]
                      [ START (integer) ]
                      [ LIMIT (integer) ]
                      [ ORDER BY ordering_clause ]
```

### WHERE

The WHERE clause specifies a filter to the result. It is composed of conditions appended with the **AND** | **OR** operator.

#### Conditions

The conditions are composed by name where the operation gonna happen, the operator then the value to be measurable.

#### Operators

The Operators ares:


| Operator | Description |
| ------------- | ------------- |
| =             | Equal to |
| >             | Greater than|
| <             | Less than |
| >=            | Greater than or equal to |
| <=            | Less than or equal to |
| &=            | Bitwise AND equals |
| **BETWEEN**   | TRUE if the operand is within the range of comparisons |
| **NOT**       | Displays a record if the condition(s) is NOT TRUE	|
| **AND**       | TRUE if all the conditions separated by AND is TRUE|
| **OR**        | TRUE if any of the conditions separated by OR is TRUE|
| **LIKE**          |TRUE if the operand matches a pattern	|

### START

The **START** option to a **SELECT** statement defines where the query should start,

### LIMIT

The **LIMIT** option to a **SELECT** statement limits the number of rows returned by a query, 

### ORDER BY

The ORDER BY clause allows selecting the order of the returned results. It takes as argument a list of column names along with the order for the column (**ASC** for ascendant and **DESC** for the descendant, omitting the order being equivalent to **ASC**). 

#### Sample:


```sql
select * from Person
select  name, age ,adress.age from Person order by name desc age desc
select  * from Person where birthday between "01-09-1988" and "01-09-1988" and salary = 12
select  name, age ,adress.age from Person start 20 limit 10 order by name desc age desc
```


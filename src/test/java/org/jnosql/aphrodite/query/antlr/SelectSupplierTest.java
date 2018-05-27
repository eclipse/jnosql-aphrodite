/*
 *  Copyright (c) 2018 Otávio Santana and others
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  and Apache License v2.0 which accompanies this distribution.
 *  The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 *  and the Apache License v2.0 is available at http://www.opensource.org/licenses/apache2.0.php.
 *  You may elect to redistribute this code under either of these licenses.
 *  Contributors:
 *  Otavio Santana
 */

package org.jnosql.aphrodite.query.antlr;

import org.jnosql.aphrodite.query.ArrayValue;
import org.jnosql.aphrodite.query.Condition;
import org.jnosql.aphrodite.query.Function;
import org.jnosql.aphrodite.query.FunctionValue;
import org.jnosql.aphrodite.query.JSONValue;
import org.jnosql.aphrodite.query.NumberValue;
import org.jnosql.aphrodite.query.ParamValue;
import org.jnosql.aphrodite.query.SelectQuery;
import org.jnosql.aphrodite.query.SelectSupplier;
import org.jnosql.aphrodite.query.Sort;
import org.jnosql.aphrodite.query.StringValue;
import org.jnosql.aphrodite.query.Value;
import org.jnosql.aphrodite.query.Where;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import javax.json.JsonObject;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.jnosql.aphrodite.query.Operator.BETWEEN;
import static org.jnosql.aphrodite.query.Operator.EQUALS;
import static org.jnosql.aphrodite.query.Operator.GREATER_EQUALS_THAN;
import static org.jnosql.aphrodite.query.Operator.GREATER_THAN;
import static org.jnosql.aphrodite.query.Operator.IN;
import static org.jnosql.aphrodite.query.Operator.LESSER_EQUALS_THAN;
import static org.jnosql.aphrodite.query.Operator.LESSER_THAN;
import static org.jnosql.aphrodite.query.Sort.SortType.ASC;
import static org.jnosql.aphrodite.query.Sort.SortType.DESC;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SelectSupplierTest {

    private SelectSupplier selectSupplier = new DefaultSelectSupplier();

    @Test
    public void shouldReturnErrorWhenStringIsNull() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            selectSupplier.apply(null);
        });
    }

    @ParameterizedTest(name = "Should parser the query {0}")
    @ValueSource(strings = {"select * from God"})
    public void shouldReturnParserQuery(String query) {
        SelectQuery selectQuery = checkSelectFromStart(query);
        assertFalse(selectQuery.getWhere().isPresent());
    }

    @ParameterizedTest(name = "Should parser the query {0}")
    @ValueSource(strings = {"select name, address from God"})
    public void shouldReturnParserQuery2(String query) {
        SelectQuery selectQuery = selectSupplier.apply(query);
        assertEquals("God", selectQuery.getEntity());
        assertFalse(selectQuery.getFields().isEmpty());
        assertThat(selectQuery.getFields(), contains("name", "address"));
        assertTrue(selectQuery.getOrderBy().isEmpty());
        assertEquals(0, selectQuery.getLimit());
        assertEquals(0, selectQuery.getSkip());
        assertFalse(selectQuery.getWhere().isPresent());
    }

    @ParameterizedTest(name = "Should parser the query {0}")
    @ValueSource(strings = {"select name, address from God order by name"})
    public void shouldReturnParserQuery3(String query) {
        SelectQuery selectQuery = selectSupplier.apply(query);
        assertEquals("God", selectQuery.getEntity());
        assertFalse(selectQuery.getFields().isEmpty());
        assertThat(selectQuery.getFields(), contains("name", "address"));
        assertFalse(selectQuery.getOrderBy().isEmpty());
        assertThat(selectQuery.getOrderBy().stream().map(Sort::getName).collect(toList()), contains("name"));
        assertThat(selectQuery.getOrderBy().stream().map(Sort::getType).collect(toList()), contains(ASC));
        assertEquals(0, selectQuery.getLimit());
        assertEquals(0, selectQuery.getSkip());
        assertFalse(selectQuery.getWhere().isPresent());
    }

    @ParameterizedTest(name = "Should parser the query {0}")
    @ValueSource(strings = {"select name, address from God order by name desc"})
    public void shouldReturnParserQuery4(String query) {
        SelectQuery selectQuery = selectSupplier.apply(query);
        assertEquals("God", selectQuery.getEntity());
        assertFalse(selectQuery.getFields().isEmpty());
        assertThat(selectQuery.getFields(), contains("name", "address"));
        assertFalse(selectQuery.getOrderBy().isEmpty());
        assertThat(selectQuery.getOrderBy().stream().map(Sort::getName).collect(toList()), contains("name"));
        assertThat(selectQuery.getOrderBy().stream().map(Sort::getType).collect(toList()), contains(DESC));
        assertEquals(0, selectQuery.getLimit());
        assertEquals(0, selectQuery.getSkip());
        assertFalse(selectQuery.getWhere().isPresent());
    }

    @ParameterizedTest(name = "Should parser the query {0}")
    @ValueSource(strings = {"select name, address from God order by name desc age asc"})
    public void shouldReturnParserQuery5(String query) {
        SelectQuery selectQuery = selectSupplier.apply(query);
        assertEquals("God", selectQuery.getEntity());
        assertFalse(selectQuery.getFields().isEmpty());
        assertThat(selectQuery.getFields(), contains("name", "address"));
        assertFalse(selectQuery.getOrderBy().isEmpty());
        assertThat(selectQuery.getOrderBy().stream().map(Sort::getName).collect(toList()), contains("name", "age"));
        assertThat(selectQuery.getOrderBy().stream().map(Sort::getType).collect(toList()), contains(DESC, ASC));
        assertEquals(0, selectQuery.getLimit());
        assertEquals(0, selectQuery.getSkip());
        assertFalse(selectQuery.getWhere().isPresent());
    }

    @ParameterizedTest(name = "Should parser the query {0}")
    @ValueSource(strings = {"select * from God skip 12"})
    public void shouldReturnParserQuery6(String query) {
        SelectQuery selectQuery = selectSupplier.apply(query);
        assertEquals("God", selectQuery.getEntity());
        assertTrue(selectQuery.getFields().isEmpty());
        assertTrue(selectQuery.getOrderBy().isEmpty());
        assertEquals(0, selectQuery.getLimit());
        assertEquals(12, selectQuery.getSkip());
        assertFalse(selectQuery.getWhere().isPresent());
    }

    @ParameterizedTest(name = "Should parser the query {0}")
    @ValueSource(strings = {"select * from God limit 12"})
    public void shouldReturnParserQuery7(String query) {
        SelectQuery selectQuery = selectSupplier.apply(query);
        assertEquals("God", selectQuery.getEntity());
        assertTrue(selectQuery.getFields().isEmpty());
        assertTrue(selectQuery.getOrderBy().isEmpty());
        assertEquals(12, selectQuery.getLimit());
        assertEquals(0, selectQuery.getSkip());
        assertFalse(selectQuery.getWhere().isPresent());
    }

    @ParameterizedTest(name = "Should parser the query {0}")
    @ValueSource(strings = {"select * from God skip 10 limit 12"})
    public void shouldReturnParserQuery8(String query) {
        SelectQuery selectQuery = selectSupplier.apply(query);
        assertEquals("God", selectQuery.getEntity());
        assertTrue(selectQuery.getFields().isEmpty());
        assertTrue(selectQuery.getOrderBy().isEmpty());
        assertEquals(12, selectQuery.getLimit());
        assertEquals(10, selectQuery.getSkip());
        assertFalse(selectQuery.getWhere().isPresent());
    }


    @ParameterizedTest(name = "Should parser the query {0}")
    @ValueSource(strings = {"select  * from God where age = 10"})
    public void shouldReturnParserQuery9(String query) {
        SelectQuery selectQuery = checkSelectFromStart(query);
        assertTrue(selectQuery.getWhere().isPresent());

        Where where = selectQuery.getWhere().get();
        Condition condition = where.getCondition();
        Value value = condition.getValue();
        assertEquals(EQUALS, condition.getOperator());
        assertEquals("age", condition.getName());
        assertTrue(value instanceof NumberValue);
        assertEquals(10L, value.get());
    }

    @ParameterizedTest(name = "Should parser the query {0}")
    @ValueSource(strings = {"select  * from God where stamina > 10.23"})
    public void shouldReturnParserQuery10(String query) {
        SelectQuery selectQuery = checkSelectFromStart(query);
        assertTrue(selectQuery.getWhere().isPresent());

        Where where = selectQuery.getWhere().get();
        Condition condition = where.getCondition();
        Value value = condition.getValue();
        assertEquals(GREATER_THAN, condition.getOperator());
        assertEquals("stamina", condition.getName());
        assertTrue(value instanceof NumberValue);
        assertEquals(10.23, value.get());
    }

    @ParameterizedTest(name = "Should parser the query {0}")
    @ValueSource(strings = {"select  * from God where stamina >= 10.23"})
    public void shouldReturnParserQuery11(String query) {
        SelectQuery selectQuery = checkSelectFromStart(query);
        assertTrue(selectQuery.getWhere().isPresent());

        Where where = selectQuery.getWhere().get();
        Condition condition = where.getCondition();
        Value value = condition.getValue();
        assertEquals(GREATER_EQUALS_THAN, condition.getOperator());
        assertEquals("stamina", condition.getName());
        assertTrue(value instanceof NumberValue);
        assertEquals(10.23, value.get());
    }

    @ParameterizedTest(name = "Should parser the query {0}")
    @ValueSource(strings = {"select  * from God where stamina <= 10.23"})
    public void shouldReturnParserQuery12(String query) {
        SelectQuery selectQuery = checkSelectFromStart(query);
        assertTrue(selectQuery.getWhere().isPresent());

        Where where = selectQuery.getWhere().get();
        Condition condition = where.getCondition();
        Value value = condition.getValue();
        assertEquals(LESSER_EQUALS_THAN, condition.getOperator());
        assertEquals("stamina", condition.getName());
        assertTrue(value instanceof NumberValue);
        assertEquals(10.23, value.get());
    }

    @ParameterizedTest(name = "Should parser the query {0}")
    @ValueSource(strings = {"select  * from God where stamina < 10.23"})
    public void shouldReturnParserQuery13(String query) {
        SelectQuery selectQuery = checkSelectFromStart(query);
        assertTrue(selectQuery.getWhere().isPresent());

        Where where = selectQuery.getWhere().get();
        Condition condition = where.getCondition();
        Value value = condition.getValue();
        assertEquals(LESSER_THAN, condition.getOperator());
        assertEquals("stamina", condition.getName());
        assertTrue(value instanceof NumberValue);
        assertEquals(10.23, value.get());
    }


    @ParameterizedTest(name = "Should parser the query {0}")
    @ValueSource(strings = {"select  * from God where age between 10 and 30"})
    public void shouldReturnParserQuery14(String query) {
        SelectQuery selectQuery = checkSelectFromStart(query);
        assertTrue(selectQuery.getWhere().isPresent());

        Where where = selectQuery.getWhere().get();
        Condition condition = where.getCondition();
        Value value = condition.getValue();
        assertEquals(BETWEEN, condition.getOperator());
        assertEquals("age", condition.getName());
        assertTrue(value instanceof ArrayValue);
        ArrayValue arrayValue = ArrayValue.class.cast(value);
        Value<?>[] values = arrayValue.get();
        assertThat(Stream.of(values).map(Value::get).collect(toList()), contains(10L, 30L));
    }

    @ParameterizedTest(name = "Should parser the query {0}")
    @ValueSource(strings = {"select  * from God where name = \"diana\""})
    public void shouldReturnParserQuery15(String query) {
        SelectQuery selectQuery = checkSelectFromStart(query);
        assertTrue(selectQuery.getWhere().isPresent());

        Where where = selectQuery.getWhere().get();
        Condition condition = where.getCondition();
        Value value = condition.getValue();
        assertEquals(EQUALS, condition.getOperator());
        assertEquals("name", condition.getName());
        assertTrue(value instanceof StringValue);
        assertEquals("diana", value.get());
    }

    @ParameterizedTest(name = "Should parser the query {0}")
    @ValueSource(strings = {"select  * from God where name = {\"diana\"}"})
    public void shouldReturnParserQuery16(String query) {
        SelectQuery selectQuery = checkSelectFromStart(query);
        assertTrue(selectQuery.getWhere().isPresent());

        Where where = selectQuery.getWhere().get();
        Condition condition = where.getCondition();
        Value value = condition.getValue();
        assertEquals(EQUALS, condition.getOperator());
        assertEquals("name", condition.getName());
        assertTrue(value instanceof ArrayValue);
        List<?> values = Stream.of(ArrayValue.class.cast(value).get()).map(Value::get).collect(toList());
        assertThat(values, contains("diana"));
    }

    @ParameterizedTest(name = "Should parser the query {0}")
    @ValueSource(strings = {"select  * from God where name = {\"diana\", 17, 20.21}"})
    public void shouldReturnParserQuery17(String query) {
        SelectQuery selectQuery = checkSelectFromStart(query);
        assertTrue(selectQuery.getWhere().isPresent());

        Where where = selectQuery.getWhere().get();
        Condition condition = where.getCondition();
        Value value = condition.getValue();
        assertEquals(EQUALS, condition.getOperator());
        assertEquals("name", condition.getName());
        assertTrue(value instanceof ArrayValue);
        List<?> values = Stream.of(ArrayValue.class.cast(value).get()).map(Value::get).collect(toList());
        assertThat(values, contains("diana", 17L, 20.21));
    }


    @ParameterizedTest(name = "Should parser the query {0}")
    @ValueSource(strings = {"select  * from God where siblings = {\"apollo\": \"Brother\", \"Zeus\": \"Father\"}"})
    public void shouldReturnParserQuery18(String query) {
        SelectQuery selectQuery = checkSelectFromStart(query);
        assertTrue(selectQuery.getWhere().isPresent());

        Where where = selectQuery.getWhere().get();
        Condition condition = where.getCondition();
        Value value = condition.getValue();
        assertEquals(EQUALS, condition.getOperator());
        assertEquals("siblings", condition.getName());
        assertTrue(value instanceof JSONValue);
        JsonObject jsonObject = JSONValue.class.cast(value).get();
        assertEquals("Brother", jsonObject.getString("apollo"));
        assertEquals("Father", jsonObject.getString("Zeus"));
    }

    @ParameterizedTest(name = "Should parser the query {0}")
    @ValueSource(strings = {"select  * from God where name = @name"})
    public void shouldReturnParserQuery19(String query) {
        SelectQuery selectQuery = checkSelectFromStart(query);
        assertTrue(selectQuery.getWhere().isPresent());

        Where where = selectQuery.getWhere().get();
        Condition condition = where.getCondition();
        Value value = condition.getValue();
        assertEquals(EQUALS, condition.getOperator());
        assertEquals("name", condition.getName());
        assertTrue(value instanceof ParamValue);
        assertEquals("name", ParamValue.class.cast(value).get());
    }

    @ParameterizedTest(name = "Should parser the query {0}")
    @ValueSource(strings = {"select  * from God where age = convert(12, java.lang.Integer)"})
    public void shouldReturnParserQuery20(String query) {
        SelectQuery selectQuery = checkSelectFromStart(query);
        assertTrue(selectQuery.getWhere().isPresent());

        Where where = selectQuery.getWhere().get();
        Condition condition = where.getCondition();
        Value value = condition.getValue();
        assertEquals(EQUALS, condition.getOperator());
        assertEquals("age", condition.getName());
        assertTrue(value instanceof FunctionValue);
        Function function = FunctionValue.class.cast(value).get();
        assertEquals("converter", function.getName());
        Object[] params = function.getParams();
        assertEquals(12L, NumberValue.class.cast(params[0]).get());
        assertEquals(Integer.class, params[1]);
    }

    @ParameterizedTest(name = "Should parser the query {0}")
    @ValueSource(strings = {"select  * from God where name in (\"Ada\", \"Apollo\")"})
    public void shouldReturnParserQuery21(String query) {
        SelectQuery selectQuery = checkSelectFromStart(query);
        assertTrue(selectQuery.getWhere().isPresent());

        Where where = selectQuery.getWhere().get();
        Condition condition = where.getCondition();
        Value value = condition.getValue();
        assertEquals(IN, condition.getOperator());
        assertEquals("name", condition.getName());
        assertTrue(value instanceof ArrayValue);
        List<?> values = Stream.of(ArrayValue.class.cast(value).get()).map(Value::get).collect(toList());
        assertThat(values, contains("Ada", "Apollo"));
    }

    private SelectQuery checkSelectFromStart(String query) {
        SelectQuery selectQuery = selectSupplier.apply(query);
        assertEquals("God", selectQuery.getEntity());
        assertTrue(selectQuery.getFields().isEmpty());
        assertTrue(selectQuery.getOrderBy().isEmpty());
        assertEquals(0, selectQuery.getLimit());
        assertEquals(0, selectQuery.getSkip());
        return selectQuery;
    }

}
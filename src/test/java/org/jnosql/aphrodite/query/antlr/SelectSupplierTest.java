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

import org.jnosql.aphrodite.query.Condition;
import org.jnosql.aphrodite.query.NumberValue;
import org.jnosql.aphrodite.query.SelectQuery;
import org.jnosql.aphrodite.query.SelectSupplier;
import org.jnosql.aphrodite.query.Sort;
import org.jnosql.aphrodite.query.Value;
import org.jnosql.aphrodite.query.Where;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
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
        assertEquals("age", condition.getName());
        assertTrue(value instanceof NumberValue);
        assertEquals(10L, value.get());
    }

    @ParameterizedTest(name = "Should parser the query {0}")
    @ValueSource(strings = {"select  * from God where stamina = 10.23"})
    public void shouldReturnParserQuery10(String query) {
        SelectQuery selectQuery = checkSelectFromStart(query);
        assertTrue(selectQuery.getWhere().isPresent());

        Where where = selectQuery.getWhere().get();
        Condition condition = where.getCondition();
        Value value = condition.getValue();
        assertEquals("stamina", condition.getName());
        assertTrue(value instanceof NumberValue);
        assertEquals(10.23, value.get());
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
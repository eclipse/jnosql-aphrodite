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
package org.jnosql.aphrodite.antlr.method;

import org.jnosql.query.Condition;
import org.jnosql.query.ConditionValue;
import org.jnosql.query.Operator;
import org.jnosql.query.ParamValue;
import org.jnosql.query.SelectQuery;
import org.jnosql.query.Value;
import org.jnosql.query.Where;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FindByMethodQuerySupplierTest {

    private FindByMethodQuerySupplier querySupplier = new FindByMethodQuerySupplier();


    @ParameterizedTest(name = "Should parser the query {0}")
    @ValueSource(strings = {"findByName"})
    public void shouldReturnParserQuery(String query) {
        String entity = "entity";
        checkEqualsQuery(query, entity);
    }

    @ParameterizedTest(name = "Should parser the query {0}")
    @ValueSource(strings = {"findByNameEquals"})
    public void shouldReturnParserQuery2(String query) {
        String entity = "entity";
        checkEqualsQuery(query, entity);
    }

    @ParameterizedTest(name = "Should parser the query {0}")
    @ValueSource(strings = {"findByNameNotEquals"})
    public void shouldReturnParserQuery3(String query) {
        checkNotCondition(query, Operator.EQUALS, "name");
    }

    @ParameterizedTest(name = "Should parser the query {0}")
    @ValueSource(strings = {"findByAgeGreaterThan"})
    public void shouldReturnParserQuery4(String query) {

        Operator operator = Operator.GREATER_THAN;
        String variable = "age";
        checkCondition(query, operator, variable);
    }


    @ParameterizedTest(name = "Should parser the query {0}")
    @ValueSource(strings = {"findByAgeNotGreaterThan"})
    public void shouldReturnParserQuery5(String query) {
        Operator operator = Operator.GREATER_THAN;
        String variable = "age";
        checkNotCondition(query, operator, variable);
    }

    private void checkNotCondition(String query, Operator operator, String variable) {
        String entity = "entity";
        SelectQuery selectQuery = querySupplier.apply(query, entity);
        assertNotNull(selectQuery);
        assertEquals(entity, selectQuery.getEntity());
        assertTrue(selectQuery.getFields().isEmpty());
        assertTrue(selectQuery.getOrderBy().isEmpty());
        assertEquals(0, selectQuery.getLimit());
        assertEquals(0, selectQuery.getSkip());
        Optional<Where> where = selectQuery.getWhere();
        assertTrue(where.isPresent());
        Condition condition = where.get().getCondition();
        Value<?> value = condition.getValue();
        assertEquals(Operator.NOT, condition.getOperator());


        assertEquals("_NOT", condition.getName());
        assertTrue(value instanceof ConditionValue);
        Condition condition1 = ConditionValue.class.cast(value).get().get(0);
        Value<?> param = condition1.getValue();
        assertEquals(operator, condition1.getOperator());
        assertTrue(ParamValue.class.cast(param).get().contains(variable));
    }

    private void checkEqualsQuery(String query, String entity) {
        SelectQuery selectQuery = querySupplier.apply(query, entity);
        assertNotNull(selectQuery);
        assertEquals(entity, selectQuery.getEntity());
        assertTrue(selectQuery.getFields().isEmpty());
        assertTrue(selectQuery.getOrderBy().isEmpty());
        assertEquals(0, selectQuery.getLimit());
        assertEquals(0, selectQuery.getSkip());
        Optional<Where> where = selectQuery.getWhere();
        assertTrue(where.isPresent());
        Condition condition = where.get().getCondition();
        Value<?> value = condition.getValue();
        assertEquals(Operator.EQUALS, condition.getOperator());
        assertEquals("name", condition.getName());
        assertTrue(value instanceof ParamValue);
        assertTrue(ParamValue.class.cast(value).get().contains("name"));
    }

    private void checkCondition(String query, Operator operator, String variable) {
        String entity = "entity";
        SelectQuery selectQuery = querySupplier.apply(query, entity);
        assertNotNull(selectQuery);
        assertEquals(entity, selectQuery.getEntity());
        assertTrue(selectQuery.getFields().isEmpty());
        assertTrue(selectQuery.getOrderBy().isEmpty());
        assertEquals(0, selectQuery.getLimit());
        assertEquals(0, selectQuery.getSkip());
        Optional<Where> where = selectQuery.getWhere();
        assertTrue(where.isPresent());
        Condition condition = where.get().getCondition();
        Value<?> value = condition.getValue();
        assertEquals(operator, condition.getOperator());
        assertTrue(ParamValue.class.cast(value).get().contains(variable));
    }
}
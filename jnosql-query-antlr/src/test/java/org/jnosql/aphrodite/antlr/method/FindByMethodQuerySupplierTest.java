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
}
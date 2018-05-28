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
package org.jnosql.aphrodite.antlr;

import org.jnosql.query.Condition;
import org.jnosql.query.InsertQuery;
import org.jnosql.query.InsertSupplier;
import org.jnosql.query.Operator;
import org.jnosql.query.StringValue;
import org.jnosql.query.Value;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InsertSupplierTest {

    private InsertSupplier insertSupplier = new DefaultInsertSupplier();


    @Test
    public void shouldReturnErrorWhenStringIsNull() {
        Assertions.assertThrows(NullPointerException.class, () -> insertSupplier.apply(null));
    }

    @ParameterizedTest(name = "Should parser the query {0}")
    @ValueSource(strings = {"insert Person (name = \"Ada Lovelace\")"})
    public void shouldReturnParserQuery(String query) {
        InsertQuery insertQuery = checkInsertFromStart(query);
        List<Condition> conditions = insertQuery.getConditions();
        assertEquals(1, conditions.size());
        Condition condition = conditions.get(0);
        assertEquals("name", condition.getName());
        assertEquals(Operator.EQUALS, condition.getOperator());
        Value<?> value = condition.getValue();
        assertTrue(value instanceof StringValue);
        assertEquals("Ada Lovelace", StringValue.class.cast(value).get());
    }

    private InsertQuery checkInsertFromStart(String query) {
        InsertQuery insertQuery = insertSupplier.apply(query);
        assertEquals("God", insertQuery.getEntity());
        return insertQuery;
    }

}

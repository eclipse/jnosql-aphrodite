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

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.jnosql.aphrodite.query.SelectQuery;
import org.jnosql.aphrodite.query.SelectSupplier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

    @Test
    @DisplayName("Should return the query 'select * from God'")
    public void shouldReturnParserQuery() {
        SelectQuery query = selectSupplier.apply("select * from God");
        assertEquals("God", query.getEntity());
        assertTrue(query.getFields().isEmpty());
        assertTrue(query.getOrderBy().isEmpty());
        assertEquals(0, query.getLimit());
        assertEquals(0, query.getSkip());
        assertFalse(query.getWhere().isPresent());
    }

    @Test
    @DisplayName("Should return the query 'select name, address from God'")
    public void shouldReturnParserQuery2() {
        SelectQuery query = selectSupplier.apply("select name, address from God");
        assertEquals("God", query.getEntity());
        assertFalse(query.getFields().isEmpty());
        MatcherAssert.assertThat(query.getFields(), Matchers.contains("name", "address"));
        assertTrue(query.getOrderBy().isEmpty());
        assertEquals(0, query.getLimit());
        assertEquals(0, query.getSkip());
        assertFalse(query.getWhere().isPresent());
    }

}
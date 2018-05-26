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

import org.jnosql.aphrodite.query.SelectQuery;
import org.jnosql.aphrodite.query.SelectSupplier;
import org.jnosql.aphrodite.query.Sort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
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

    @ParameterizedTest
    @DisplayName("Should return the query 'select * from God'")
    @ValueSource(strings = { "select * from God" })
    public void shouldReturnParserQuery(String query) {
        SelectQuery selectQuery = selectSupplier.apply(query);
        assertEquals("God", selectQuery.getEntity());
        assertTrue(selectQuery.getFields().isEmpty());
        assertTrue(selectQuery.getOrderBy().isEmpty());
        assertEquals(0, selectQuery.getLimit());
        assertEquals(0, selectQuery.getSkip());
        assertFalse(selectQuery.getWhere().isPresent());
    }

    @ParameterizedTest
    @DisplayName("Should return the query 'select name, address from God'")
    @ValueSource(strings = { "select name, address from God" })
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

    @ParameterizedTest
    @DisplayName("Should return the query 'select name, address from God order by name order by name'")
    @ValueSource(strings = { "select name, address from God order by name" })
    public void shouldReturnParserQuery3(String query) {
        SelectQuery selectQuery = selectSupplier.apply(query);
        assertEquals("God", selectQuery.getEntity());
        assertFalse(selectQuery.getFields().isEmpty());
        assertThat(selectQuery.getFields(), contains("name", "address"));
        assertFalse(selectQuery.getOrderBy().isEmpty());
        assertThat(selectQuery.getOrderBy().stream().map(Sort::getName).collect(toList()), contains("name"));
        assertThat(selectQuery.getOrderBy().stream().map(Sort::getType).collect(toList()), contains(Sort.SortType.ASC));
        assertEquals(0, selectQuery.getLimit());
        assertEquals(0, selectQuery.getSkip());
        assertFalse(selectQuery.getWhere().isPresent());
    }
    /*select  name, age ,adress.age from Person order by name
    select  name, age ,adress.age from Person order by name asc
    select  name, age ,adress.age from Person order by name desc
    select  name, age ,adress.age from Person order by name asc age asc*/

}
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

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class MethodQueryTest {

    @Test
    public void shouldReturnNullWhenQueryIsNull() {
        Assertions.assertThrows(NullPointerException.class, () ->{
           MethodQuery.of(null);
        });
    }

    @ParameterizedTest(name = "Should parser the query {0}")
    @ValueSource(strings = {"findByAge"})
    public void shouldRunQuery(String query) {
        MethodQuery methodQuery = MethodQuery.of(query);
        assertNotNull(methodQuery);
        assertEquals("findBy Age", methodQuery.get());
    }


    @ParameterizedTest(name = "Should parser the query {0}")
    @ValueSource(strings = {"findByNameAndAge"})
    public void shouldRunQuery1(String query) {
        MethodQuery methodQuery = MethodQuery.of(query);
        assertNotNull(methodQuery);
        assertEquals("findBy Name and Age", methodQuery.get());
    }

    @ParameterizedTest(name = "Should parser the query {0}")
    @ValueSource(strings = {"findByNameOrAge"})
    public void shouldRunQuery2(String query) {
        MethodQuery methodQuery = MethodQuery.of(query);
        assertNotNull(methodQuery);
        assertEquals("findBy Name or Age", methodQuery.get());
    }

    @ParameterizedTest(name = "Should parser the query {0}")
    @ValueSource(strings = {"findByNameOrAgeOrderByName"})
    public void shouldRunQuery3(String query) {
        MethodQuery methodQuery = MethodQuery.of(query);
        assertNotNull(methodQuery);
        assertEquals("findBy Name or Age order by Name", methodQuery.get());
    }

    @ParameterizedTest(name = "Should parser the query {0}")
    @ValueSource(strings = {"findByNameOrAgeOrderByNameAsc"})
    public void shouldRunQuery4(String query) {
        MethodQuery methodQuery = MethodQuery.of(query);
        assertNotNull(methodQuery);
        assertEquals("findBy Name or Age order by Name asc", methodQuery.get());
    }

    @ParameterizedTest(name = "Should parser the query {0}")
    @ValueSource(strings = {"findByNameOrAgeOrderByNameDesc"})
    public void shouldRunQuery5(String query) {
        MethodQuery methodQuery = MethodQuery.of(query);
        assertNotNull(methodQuery);
        assertEquals("findBy Name or Age order by Name desc", methodQuery.get());
    }


    @ParameterizedTest(name = "Should parser the query {0}")
    @ValueSource(strings = {"findByLastnameAndFirstname"})
    public void shouldRunQuery6(String query) {
        MethodQuery methodQuery = MethodQuery.of(query);
        assertNotNull(methodQuery);
        assertEquals("findBy Lastname and Firstname", methodQuery.get());
    }


    @ParameterizedTest(name = "Should parser the query {0}")
    @ValueSource(strings = {"findByLastnameOrFirstname"})
    public void shouldRunQuery7(String query) {
        MethodQuery methodQuery = MethodQuery.of(query);
        assertNotNull(methodQuery);
        assertEquals("findBy Lastname or Firstname", methodQuery.get());
    }

    @ParameterizedTest(name = "Should parser the query {0}")
    @ValueSource(strings = {"findByStartDateBetween"})
    public void shouldRunQuery8(String query) {
        MethodQuery methodQuery = MethodQuery.of(query);
        assertNotNull(methodQuery);
        assertEquals("findBy StartDate between ? and ?", methodQuery.get());
    }

    @ParameterizedTest(name = "Should parser the query {0}")
    @ValueSource(strings = {"findByAgeLessThan"})
    public void shouldRunQuery9(String query) {
        MethodQuery methodQuery = MethodQuery.of(query);
        assertNotNull(methodQuery);
        assertEquals("findBy Age < ?", methodQuery.get());
    }

    @ParameterizedTest(name = "Should parser the query {0}")
    @ValueSource(strings = {"findByAgeLessThanEqual"})
    public void shouldRunQuery10(String query) {
        MethodQuery methodQuery = MethodQuery.of(query);
        assertNotNull(methodQuery);
        assertEquals("findBy Age <= ?", methodQuery.get());
    }

    @ParameterizedTest(name = "Should parser the query {0}")
    @ValueSource(strings = {"findByAgeGreaterThan"})
    public void shouldRunQuery11(String query) {
        MethodQuery methodQuery = MethodQuery.of(query);
        assertNotNull(methodQuery);
        assertEquals("findBy Age > ?", methodQuery.get());
    }

    @ParameterizedTest(name = "Should parser the query {0}")
    @ValueSource(strings = {"findByAgeGreaterThanEqual"})
    public void shouldRunQuery12(String query) {
        MethodQuery methodQuery = MethodQuery.of(query);
        assertNotNull(methodQuery);
        assertEquals("findBy Age >= ?", methodQuery.get());
    }

    @ParameterizedTest(name = "Should parser the query {0}")
    @ValueSource(strings = {"findByFirstnameLike"})
    public void shouldRunQuery13(String query) {
        MethodQuery methodQuery = MethodQuery.of(query);
        assertNotNull(methodQuery);
        assertEquals("findBy Firstname like ?", methodQuery.get());
    }

    @ParameterizedTest(name = "Should parser the query {0}")
    @ValueSource(strings = {"findByFirstnameNotLike"})
    public void shouldRunQuery14(String query) {
        MethodQuery methodQuery = MethodQuery.of(query);
        assertNotNull(methodQuery);
        assertEquals("findBy Firstname not like ?", methodQuery.get());
    }
}
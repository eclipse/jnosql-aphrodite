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

import org.jnosql.query.SelectQuery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class FindByMethodQuerySupplierTest {

    private FindByMethodQuerySupplier querySupplier = new FindByMethodQuerySupplier();


    @ParameterizedTest(name = "Should parser the query {0}")
    @ValueSource(strings = {"findByAge"})
    public void shouldReturnParserQuery(String query) {
        String entity = "entity";
        SelectQuery selectQuery = querySupplier.apply(query, entity);
        Assertions.assertNotNull(selectQuery);
        Assertions.assertEquals(entity, selectQuery.getEntity());
        Assertions.assertTrue(selectQuery.getFields().isEmpty());


    }
}
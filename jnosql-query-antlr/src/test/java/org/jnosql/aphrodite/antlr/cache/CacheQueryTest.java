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
package org.jnosql.aphrodite.antlr.cache;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CacheQueryTest {

    private CacheQuery<Integer> cacheQuery;

    @BeforeEach
    public void setUp() {
        cacheQuery = CacheQuery.of(Integer::parseInt);
    }


    @Test
    public void shouldPut() {

        int total = 1_000_000;
        for (int index = 0; index < total; index++) {
            Integer value = cacheQuery.get(Integer.toString(index));
            assertNotNull(value);
            assertEquals(Integer.valueOf(index), value);
        }
        assertTrue(cacheQuery.size() < total);
    }
}
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

import java.util.Objects;
import java.util.function.Supplier;

public class MethodQuery implements Supplier<String> {

    private final String value;

    private MethodQuery(String value) {
        this.value = value
                .replaceAll("findBy", "findBy ")
                .replaceAll("OrderBy", " OrderBy ")
                .replaceAll("And", " And ")
                .replaceAll("Or(?!der)", " Or ")
                .replaceAll("Not", " Not")
                .replaceAll("Equals", " Equals")
                .replaceAll("GreaterThanEqual", " GreaterThanEqual")
                .replaceAll("LessThanEqual", " LessThanEqual")
                .replaceAll("GreaterThan", " GreaterThan" )
                .replaceAll("LessThan", " LessThan" )
                .replaceAll("Between", " Between")
                .replaceAll("In", " In" )
                .replaceAll("Like", " Like" )
                .replaceAll("Asc", " Asc ")
                .replaceAll("Desc", " Desc ").trim();

    }

    @Override
    public String get() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MethodQuery that = (MethodQuery) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    public static MethodQuery of(String query) {
        Objects.requireNonNull(query, "query is required");
        return new MethodQuery(query);
    }
}

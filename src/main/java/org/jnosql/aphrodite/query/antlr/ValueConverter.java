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

import org.jnosql.aphrodite.query.QueryException;
import org.jnosql.aphrodite.query.Value;

import java.util.Objects;

final class ValueConverter {
    private ValueConverter() {
    }

    static Value<?> get(SelectParser.ValueContext context) {
        if (Objects.nonNull(context.number())) {
            return DefaultNumberValue.of(context.number());
        }
        if(Objects.nonNull(context.string())) {
            return DefaultStringValue.of(context.string());
        }

        throw new QueryException("There is an error when trying to convert the value");
    }
}

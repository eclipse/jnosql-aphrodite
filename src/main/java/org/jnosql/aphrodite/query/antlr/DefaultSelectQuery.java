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
import org.jnosql.aphrodite.query.Sort;
import org.jnosql.aphrodite.query.Where;

import java.util.List;
import java.util.Optional;

final class DefaultSelectQuery implements SelectQuery {

    private final String entity;

    private final List<String> fields;

    private final List<Sort> sorts;

    DefaultSelectQuery(String entity, List<String> fields, List<Sort> sorts) {
        this.entity = entity;
        this.fields = fields;
        this.sorts = sorts;
    }

    @Override
    public List<String> getFields() {
        return fields;
    }

    @Override
    public String getEntity() {
        return entity;
    }

    @Override
    public Optional<Where> getWhere() {
        return Optional.empty();
    }

    @Override
    public long getSkip() {
        return 0;
    }

    @Override
    public long getLimit() {
        return 0;
    }

    @Override
    public List<Sort> getOrderBy() {
        return sorts;
    }

}

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

import org.antlr.v4.runtime.tree.ParseTree;
import org.jnosql.query.Condition;
import org.jnosql.query.InsertQuery;
import org.jnosql.query.InsertSupplier;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

class DefaultInsertSupplier extends AbstractSupplier implements InsertSupplier {

    private String entity;

    private List<Condition> conditions = new ArrayList<>();

    private Duration duration;

    @Override
    Function<QueryParser, ParseTree> getParserTree() {
        return QueryParser::insert;
    }

    @Override
    public void exitEntity(QueryParser.EntityContext ctx) {
        this.entity = ctx.getText();
    }

    @Override
    public void exitChanges(QueryParser.ChangesContext ctx) {
        ctx.
    }


    @Override
    public InsertQuery apply(String query) {
        runQuery(query);
        return null;
    }
}

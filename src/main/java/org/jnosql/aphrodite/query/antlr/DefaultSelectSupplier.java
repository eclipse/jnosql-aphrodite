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

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.jnosql.aphrodite.query.SelectQuery;
import org.jnosql.aphrodite.query.SelectSupplier;
import org.jnosql.aphrodite.query.Sort;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

public class DefaultSelectSupplier extends SelectBaseListener implements SelectSupplier {

    private String entity;

    private List<String> fields = emptyList();

    private List<Sort> sorts = emptyList();

    private long skip;

    private long limit;

    @Override
    public void exitFields(SelectParser.FieldsContext ctx) {
        this.fields = ctx.name().stream().map(SelectParser.NameContext::getText).collect(toList());
    }

    @Override
    public void exitSkip(SelectParser.SkipContext ctx) {
        System.out.println(ctx);
    }

    @Override
    public void exitLimit(SelectParser.LimitContext ctx) {
        System.out.println(ctx);
    }

    @Override
    public void exitEntity(SelectParser.EntityContext ctx) {
        this.entity = ctx.getText();
    }

    @Override
    public void enterOrder(SelectParser.OrderContext ctx) {
        this.sorts = ctx.orderName().stream().map(DefaultSort::of).collect(Collectors.toList());
    }



    @Override
    public SelectQuery apply(String query) {
        Objects.requireNonNull(query, "query is required");

        CharStream stream = CharStreams.fromString(query);
        SelectLexer lexer = new SelectLexer(stream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        SelectParser parser = new SelectParser(tokens);
        lexer.addErrorListener(QueryErrorListener.INSTANCE);
        parser.addErrorListener(QueryErrorListener.INSTANCE);

        ParseTree tree = parser.query();
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(this, tree);
        return new DefaultSelectQuery(entity, fields, sorts, skip, limit);
    }
}
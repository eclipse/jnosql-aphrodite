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
import org.jnosql.aphrodite.query.ArrayValue;
import org.jnosql.aphrodite.query.Condition;
import org.jnosql.aphrodite.query.ConditionValue;
import org.jnosql.aphrodite.query.Operator;
import org.jnosql.aphrodite.query.SelectQuery;
import org.jnosql.aphrodite.query.SelectSupplier;
import org.jnosql.aphrodite.query.Sort;
import org.jnosql.aphrodite.query.StringValue;
import org.jnosql.aphrodite.query.Value;
import org.jnosql.aphrodite.query.Where;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;
import static org.jnosql.aphrodite.query.Operator.AND;
import static org.jnosql.aphrodite.query.Operator.BETWEEN;
import static org.jnosql.aphrodite.query.Operator.EQUALS;
import static org.jnosql.aphrodite.query.Operator.GREATER_EQUALS_THAN;
import static org.jnosql.aphrodite.query.Operator.GREATER_THAN;
import static org.jnosql.aphrodite.query.Operator.IN;
import static org.jnosql.aphrodite.query.Operator.LESSER_EQUALS_THAN;
import static org.jnosql.aphrodite.query.Operator.LESSER_THAN;
import static org.jnosql.aphrodite.query.Operator.LIKE;
import static org.jnosql.aphrodite.query.Operator.NOT;
import static org.jnosql.aphrodite.query.Operator.OR;

public class DefaultSelectSupplier extends QueryBaseListener implements SelectSupplier {

    private String entity;

    private List<String> fields = emptyList();

    private List<Sort> sorts = emptyList();

    private long skip;

    private long limit;

    private Where where;

    private Condition condition;

    private boolean and = true;

    @Override
    public void exitFields(QueryParser.FieldsContext ctx) {
        this.fields = ctx.name().stream().map(QueryParser.NameContext::getText).collect(toList());
    }

    @Override
    public void exitSkip(QueryParser.SkipContext ctx) {
        this.skip = Long.valueOf(ctx.INT().getText());
    }

    @Override
    public void exitLimit(QueryParser.LimitContext ctx) {
        this.limit = Long.valueOf(ctx.INT().getText());
    }

    @Override
    public void exitEntity(QueryParser.EntityContext ctx) {
        this.entity = ctx.getText();
    }

    @Override
    public void enterOrder(QueryParser.OrderContext ctx) {
        this.sorts = ctx.orderName().stream().map(DefaultSort::of).collect(Collectors.toList());
    }

    @Override
    public void exitEq(QueryParser.EqContext ctx) {
        boolean hasNot = Objects.nonNull(ctx.not());
        String name = ctx.name().getText();
        Value<?> value = ValueConverter.get(ctx.value());
        checkCondition(new DefaultCondition(name, EQUALS, value), hasNot);
    }

    @Override
    public void exitLt(QueryParser.LtContext ctx) {
        boolean hasNot = Objects.nonNull(ctx.not());
        String name = ctx.name().getText();
        Value<?> value = ValueConverter.get(ctx.value());
        checkCondition(new DefaultCondition(name, LESSER_THAN, value), hasNot);
    }

    @Override
    public void exitLte(QueryParser.LteContext ctx) {
        boolean hasNot = Objects.nonNull(ctx.not());
        String name = ctx.name().getText();
        Value<?> value = ValueConverter.get(ctx.value());
        checkCondition(new DefaultCondition(name, LESSER_EQUALS_THAN, value), hasNot);
    }

    @Override
    public void exitGt(QueryParser.GtContext ctx) {
        boolean hasNot = Objects.nonNull(ctx.not());
        String name = ctx.name().getText();
        Value<?> value = ValueConverter.get(ctx.value());
        checkCondition(new DefaultCondition(name, GREATER_THAN, value), hasNot);
    }

    @Override
    public void exitGte(QueryParser.GteContext ctx) {
        boolean hasNot = Objects.nonNull(ctx.not());
        String name = ctx.name().getText();
        Value<?> value = ValueConverter.get(ctx.value());
        checkCondition(new DefaultCondition(name, GREATER_EQUALS_THAN, value), hasNot);
    }

    @Override
    public void exitIn(QueryParser.InContext ctx) {
        boolean hasNot = Objects.nonNull(ctx.not());
        String name = ctx.name().getText();
        Value<?>[] values = ctx.value().stream()
                .map(ValueConverter::get)
                .toArray(Value[]::new);
        ArrayValue value = DefaultArrayValue.of(values);
        checkCondition(new DefaultCondition(name, IN, value), hasNot);
    }


    @Override
    public void exitLike(QueryParser.LikeContext ctx) {
        boolean hasNot = Objects.nonNull(ctx.not());
        String name = ctx.name().getText();
        StringValue value = DefaultStringValue.of(ctx.string());
        checkCondition(new DefaultCondition(name, LIKE, value), hasNot);
    }

    @Override
    public void exitBetween(QueryParser.BetweenContext ctx) {
        boolean hasNot = Objects.nonNull(ctx.not());
        String name = ctx.name().getText();
        Value<?>[] values = ctx.value().stream().map(ValueConverter::get).toArray(Value[]::new);
        checkCondition(new DefaultCondition(name, BETWEEN, DefaultArrayValue.of(values)), hasNot);
    }

    @Override
    public void exitAnd(QueryParser.AndContext ctx) {
        this.and = true;
    }

    @Override
    public void exitOr(QueryParser.OrContext ctx) {
        this.and = false;
    }

    private void checkCondition(Condition condition, boolean hasNot) {
        Condition newCondition = checkNotCondition(condition, hasNot);
        if (Objects.isNull(this.condition)) {
            this.condition = newCondition;
            return;
        }
        if (and) {
            appendCondition(AND, newCondition);
        } else {
            appendCondition(OR, newCondition);
        }

    }

    private void appendCondition(Operator operator, Condition newCondition) {

        if (operator.equals(this.condition.getOperator())) {
            ConditionValue conditionValue = ConditionValue.class.cast(this.condition.getValue());
            List<Condition> conditions = new ArrayList<>(conditionValue.get());
            conditions.add(newCondition);
            this.condition = new DefaultCondition("_" + operator.name(), operator, DefaultConditionValue.of(conditions));
        } else if (isNotAppendable()) {
            List<Condition> conditions = Arrays.asList(this.condition, newCondition);
            this.condition = new DefaultCondition("_" + operator.name(), operator, DefaultConditionValue.of(conditions));
        } else {
            List<Condition> conditions = ConditionValue.class.cast(this.condition.getValue()).get();
            Condition lastCondition = conditions.get(conditions.size() - 1);

            if (isAppendable(lastCondition) && operator.equals(lastCondition.getOperator())) {
                List<Condition> lastConditions = new ArrayList<>(ConditionValue.class.cast(lastCondition.getValue()).get());
                lastConditions.add(newCondition);

                Condition newAppendable = new DefaultCondition("_" + operator.name(),
                        operator, DefaultConditionValue.of(lastConditions));

                List<Condition> newConditions = new ArrayList<>(conditions.subList(0, conditions.size() - 1));
                newConditions.add(newAppendable);
                this.condition = new DefaultCondition(this.condition.getName(), this.condition.getOperator(),
                        DefaultConditionValue.of(newConditions));
            } else {
                Condition newAppendable = new DefaultCondition("_" + operator.name(),
                        operator, DefaultConditionValue.of(Collections.singletonList(newCondition)));

                List<Condition> newConditions = new ArrayList<>(conditions);
                newConditions.add(newAppendable);
                this.condition = new DefaultCondition(this.condition.getName(), this.condition.getOperator(),
                        DefaultConditionValue.of(newConditions));
            }

        }
    }

    private boolean isAppendable(Condition condition) {
        return (AND.equals(condition.getOperator()) || OR.equals(condition.getOperator()));
    }

    private boolean isNotAppendable() {
        return !isAppendable(this.condition);
    }

    private Condition checkNotCondition(Condition condition, boolean hasNot) {
        if (hasNot) {
            ConditionValue conditions = DefaultConditionValue.of(Collections.singletonList(condition));
            return new DefaultCondition("_NOT", NOT, conditions);
        } else {
            return condition;
        }
    }

    @Override
    public SelectQuery apply(String query) {
        Objects.requireNonNull(query, "query is required");

        CharStream stream = CharStreams.fromString(query);
        QueryLexer lexer = new QueryLexer(stream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        QueryParser parser = new QueryParser(tokens);
        lexer.removeErrorListeners();
        parser.removeErrorListeners();
        lexer.addErrorListener(QueryErrorListener.INSTANCE);
        parser.addErrorListener(QueryErrorListener.INSTANCE);

        ParseTree tree = parser.select();
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(this, tree);
        if (Objects.nonNull(condition)) {
            this.where = new DefaultWhere(condition);
        }
        return new DefaultSelectQuery(entity, fields, sorts, skip, limit, where);
    }
}
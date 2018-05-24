package org.jnosql.aphrodite.query;

import org.antlr.v4.runtime.RuleContext;

import java.util.Optional;

public class SelectWalker extends SelectBaseListener {

    public void enterR(SelectParser.QueryContext ctx) {
        Optional.ofNullable(ctx.fields())
                .map(RuleContext::getText)
                .ifPresent(entity -> System.out.println("fields : " + entity));

        Optional.ofNullable(ctx.entity())
                .map(RuleContext::getText)
                .ifPresent(entity -> System.out.println("entity : " + entity));


    }

    public void exitR(SelectParser.QueryContext ctx) {
    }
}
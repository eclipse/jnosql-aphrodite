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
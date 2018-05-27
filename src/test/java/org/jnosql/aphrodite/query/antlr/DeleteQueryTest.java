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
import org.jnosql.aphrodite.query.QueryException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.logging.Logger;

public class DeleteQueryTest {

    private static final Logger LOGGER = Logger.getLogger(DeleteQueryTest.class.getName());

    @ParameterizedTest
    @ArgumentsSource(DeleteQueryArgumentProvider.class)
    public void shouldExecuteQuery(String query) {
        LOGGER.info("query: " + query);
        testQuery(query);
    }

    @Test
    public void shouldIgnoreComments() {
        testQuery("//ignore this line \n select * from Person");
    }

    @ParameterizedTest(name = "Should parser the query {0}")
    @ArgumentsSource(WrongSelectQueryArgumentProvider.class)
    public void shouldNotExecute(String query) {
//        Assertions.assertThrows(QueryException.class, () -> testQuery(query));
    }

    private void testQuery(String query) {
        CharStream stream = CharStreams.fromString(query);
        DeleteLexer lexer = new DeleteLexer(stream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        DeleteParser parser = new DeleteParser(tokens);
        lexer.removeErrorListeners();
        parser.removeErrorListeners();
        lexer.addErrorListener(QueryErrorListener.INSTANCE);
        parser.addErrorListener(QueryErrorListener.INSTANCE);

        ParseTree tree = parser.query();
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(new DeleteBaseListener(), tree);


    }


}

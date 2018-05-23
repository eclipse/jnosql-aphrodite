package org.jnosql.query;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.logging.Logger;

public class QueryTest {

    private static Logger LOGGER = Logger.getLogger(QueryTest.class.getName());

    @ParameterizedTest
    @ArgumentsSource(QueryArgumentProvider.class)
    public void shouldExecuteQuery(String query) {
        LOGGER.info("Query: " + query);
        testQuery(query);
    }

    @Test
    public void shouldIgnoreComments() {
        testQuery("//ignore this line \n select * from Person");
    }

    @ParameterizedTest
    @ArgumentsSource(WrongQueryArgumentProvider.class)
    public void shouldNotExecute(String query) {
        LOGGER.info("Query: " + query);
        Assertions.assertThrows(JNoSQLQueryExecption.class, () -> {
            testQuery(query);
        });
    }

    private void testQuery(String query) {
        CharStream stream = CharStreams.fromString(query);
        SelectLexer lexer = new SelectLexer(stream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        SelectParser parser = new SelectParser(tokens);
        lexer.addErrorListener(QueryErrorListener.INSTANCE);
        parser.addErrorListener(QueryErrorListener.INSTANCE);

        ParseTree tree = parser.query();
        ParseTreeWalker walker = new ParseTreeWalker();


        walker.walk(new SelectWalker(), tree);


    }


}

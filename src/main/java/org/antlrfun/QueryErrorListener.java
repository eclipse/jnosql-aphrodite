package org.antlrfun;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.misc.ParseCancellationException;

public class QueryErrorListener  extends BaseErrorListener {
    public static final QueryErrorListener INSTANCE = new QueryErrorListener();

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine,
                            String msg, RecognitionException e)
            throws ParseCancellationException {
        throw new JNoSQLQueryExecption("line " + line + ":" + charPositionInLine + " " + msg);
    }
}
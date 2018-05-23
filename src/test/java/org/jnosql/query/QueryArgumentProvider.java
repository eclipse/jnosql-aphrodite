package org.jnosql.query;

import java.util.function.Predicate;

class QueryArgumentProvider extends AbstractArgumentProvider {

    private static final Predicate<String> IS_COMMENT = s -> s.startsWith("#");

    @Override
    protected String getFile() {
        return "/jnosql.nosql";
    }

}

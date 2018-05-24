package org.jnosql.aphrodite.query;

class WrongQueryArgumentProvider extends AbstractArgumentProvider{

    @Override
    protected String getFile() {
        return "/error.nosql";
    }
}

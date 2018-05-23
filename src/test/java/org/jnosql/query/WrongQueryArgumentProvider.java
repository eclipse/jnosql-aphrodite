package org.jnosql.query;

class WrongQueryArgumentProvider extends AbstractArgumentProvider{

    @Override
    protected String getFile() {
        return "/error.nosql";
    }
}

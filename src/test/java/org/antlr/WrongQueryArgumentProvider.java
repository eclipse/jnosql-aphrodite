package org.antlr;

class WrongQueryArgumentProvider extends AbstractArgumentProvider{

    @Override
    protected String getFile() {
        return "/error.nosql";
    }
}

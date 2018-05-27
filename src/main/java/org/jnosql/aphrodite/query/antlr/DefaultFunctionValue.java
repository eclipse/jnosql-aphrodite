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

import org.jnosql.aphrodite.query.Function;
import org.jnosql.aphrodite.query.FunctionValue;
import org.jnosql.aphrodite.query.QueryException;
import org.jnosql.aphrodite.query.Value;

import java.util.Objects;

final class DefaultFunctionValue implements FunctionValue {

    private final Function function;

    private DefaultFunctionValue(Function function) {
        this.function = function;
    }

    @Override
    public Function get() {
        return function;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DefaultFunctionValue)) {
            return false;
        }
        DefaultFunctionValue that = (DefaultFunctionValue) o;
        return Objects.equals(function, that.function);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(function);
    }

    @Override
    public String toString() {
        return function.toString();
    }

    static FunctionValue of(SelectParser.FunctionContext context) {
        if (Objects.nonNull(context.converter())) {
            SelectParser.ConverterContext converter = context.converter();
            Value<?> value = Elements.getElement(converter.element());
            String text = converter.name().getText();
            return getFunctionValue(value, text);
        }
        throw new UnsupportedOperationException("There is not support to this function yet");
    }

    static FunctionValue of(DeleteParser.FunctionContext context) {
        if (Objects.nonNull(context.converter())) {
            DeleteParser.ConverterContext converter = context.converter();
            Value<?> value = Elements.getElement(converter.element());
            String text = converter.name().getText();
            return getFunctionValue(value, text);
        }
        throw new UnsupportedOperationException("There is not support to this function yet");
    }

    private static FunctionValue getFunctionValue(Value<?> value, String text) {
        try {
            Object[] params = new Object[]{value, Class.forName(text.toString())};
            Function function = DefaultFunction.of("converter", params);
            return new DefaultFunctionValue(function);
        } catch (ClassNotFoundException e) {
            throw new QueryException("Class does not found the converter function argument: " + text, e);
        }
    }
}

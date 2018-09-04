package org.jnosql.aphrodite.antlr.method;

import org.jnosql.query.Condition;
import org.jnosql.query.ConditionValue;

import java.util.List;
import java.util.Objects;

import static java.util.Collections.unmodifiableList;

final class MethodConditionValue implements ConditionValue{

    private final List<Condition> conditions;

    private MethodConditionValue(List<Condition> conditions) {
        this.conditions = conditions;
    }

    @Override
    public List<Condition> get() {
        return unmodifiableList(conditions);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MethodConditionValue)) {
            return false;
        }
        MethodConditionValue that = (MethodConditionValue) o;
        return Objects.equals(conditions, that.conditions);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(conditions);
    }

    @Override
    public String toString() {
        return conditions.toString();
    }

    public static ConditionValue of(List<Condition> conditions) {
        return new MethodConditionValue(conditions);
    }
}

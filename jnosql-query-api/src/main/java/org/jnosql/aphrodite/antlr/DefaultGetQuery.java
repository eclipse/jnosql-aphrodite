package org.jnosql.aphrodite.antlr;

import org.jnosql.query.GetQuery;
import org.jnosql.query.Value;

import java.util.List;
import java.util.Objects;

import static java.util.Collections.unmodifiableList;

final class DefaultGetQuery implements GetQuery {

    private final List<Value<?>> keys;

    DefaultGetQuery(List<Value<?>> keys) {
        this.keys = keys;
    }

    @Override
    public List<Value<?>> getKeys() {
        return unmodifiableList(keys);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DefaultGetQuery)) {
            return false;
        }
        DefaultGetQuery that = (DefaultGetQuery) o;
        return Objects.equals(keys, that.keys);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(keys);
    }

    @Override
    public String toString() {
        return this.keys.toString();
    }
}

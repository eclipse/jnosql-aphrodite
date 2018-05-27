package org.jnosql.aphrodite.query.antlr;

import org.jnosql.aphrodite.query.DeleteQuery;
import org.jnosql.aphrodite.query.Where;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

final class DefaultDeleteQuery implements DeleteQuery {

    private final String entity;

    private final List<String> fields;

    private final Where where;

    DefaultDeleteQuery(String entity, List<String> fields, Where where) {
        this.entity = entity;
        this.fields = fields;
        this.where = where;
    }

    @Override
    public List<String> getFields() {
        return Collections.unmodifiableList(fields);
    }

    @Override
    public String getEntity() {
        return entity;
    }

    @Override
    public Optional<Where> getWhere() {
        return Optional.ofNullable(where);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DefaultDeleteQuery)) {
            return false;
        }
        DefaultDeleteQuery that = (DefaultDeleteQuery) o;
        return Objects.equals(entity, that.entity) &&
                Objects.equals(fields, that.fields) &&
                Objects.equals(where, that.where);
    }

    @Override
    public int hashCode() {
        return Objects.hash(entity, fields, where);
    }
}

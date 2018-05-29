package org.jnosql.aphrodite.antlr;

import org.jnosql.query.PutQuery;
import org.jnosql.query.Value;

import java.time.Duration;
import java.util.Optional;

final class DefaultPutQuery implements PutQuery {

    private final Value<?> key;

    private final Value<?> value;

    private final Duration ttl;

    DefaultPutQuery(Value<?> key, Value<?> value, Duration ttl) {
        this.key = key;
        this.value = value;
        this.ttl = ttl;
    }

    @Override
    public Value<?> getKey() {
        return key;
    }

    @Override
    public Value<?> getValue() {
        return value;
    }

    @Override
    public Optional<Duration> getTtl() {
        return Optional.ofNullable(ttl);
    }


    @Override
    public String toString() {
        StringBuilder put = new StringBuilder();
        put.append("put {").append(key).append(" , ").append(value);
        if (ttl != null) {
            put.append(",").append(ttl);
        }
        put.append("}");
        return put.toString();
    }
}

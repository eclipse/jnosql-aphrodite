package org.antlr;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

class QueryArgumentProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        URL url = QueryArgumentProvider.class.getResource("/jnosql.sql");
        Path path = Paths.get(url.toURI());
        return Files.readAllLines(path).stream().map(Arguments::of);
    }
}

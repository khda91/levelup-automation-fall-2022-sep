package ru.levelp.at.lesson15;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class CsvDataProvider {

    static Stream<Arguments> csvDataProvider() {
        List<String> data = new ArrayList<>();
        try {
            data.addAll(Files.readAllLines(Path.of("test.csv")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        List<Arguments> collect = data.stream()
                                      .map(str -> Arguments.of(str.split(";")))
                                      .collect(Collectors.toList());
        return Stream.of(collect.toArray(new Arguments[0]));
    }

    @ParameterizedTest
    @MethodSource("csvDataProvider")
    void test(Integer a, Integer b, Integer expectedResult) {
        var actualResult = a + b;
        assertThat(actualResult).isEqualTo(expectedResult);
    }
}

package ru.levelp.at.lesson12.design.patterns.strategy;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CommonTest {

    static Stream<Arguments> dataProvider() {
        return Stream.of(
            Arguments.of(new TaxCalculatorProviderImpl(new RuTaxCalculator()),
                new BigDecimal("4999999"), new BigDecimal("649999.87")),
            Arguments.of(new TaxCalculatorProviderImpl(new TuTaxCalculator()),
                new BigDecimal("45000"), new BigDecimal("9000.00")),
            Arguments.of(new TaxCalculatorProviderImpl(new TuTaxCalculator()),
                new BigDecimal("250000"), new BigDecimal("87500.00"))
        );
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    void common(TaxCalculatorProvider provider, BigDecimal income, BigDecimal expected) {
        var actual = provider.calculate(income);
        assertThat(actual).isEqualTo(expected);
    }
}

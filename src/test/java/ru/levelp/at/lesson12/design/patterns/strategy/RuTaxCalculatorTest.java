package ru.levelp.at.lesson12.design.patterns.strategy;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RuTaxCalculatorTest {

    private TaxCalculatorProvider provider;

    @BeforeEach
    void setUp() {
        provider = new TaxCalculatorProviderImpl(new RuTaxCalculator());
    }

    @Test
    void standardTaxRate() {
        final BigDecimal income = new BigDecimal("4999999");
        final BigDecimal expected = new BigDecimal("649999.87");
        var actual = provider.calculate(income);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void increasedTaxRate() {
        final BigDecimal income = new BigDecimal("5000001");
        final BigDecimal expected = new BigDecimal("750000.15");
        var actual = provider.calculate(income);
        assertThat(actual).isEqualTo(expected);
    }
}

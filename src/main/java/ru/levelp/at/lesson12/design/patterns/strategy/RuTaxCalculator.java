package ru.levelp.at.lesson12.design.patterns.strategy;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class RuTaxCalculator implements TaxCalculator {

    private static final BigDecimal STANDARD_TAX_RATE = new BigDecimal("0.13");
    private static final BigDecimal INCREASED_TAX_RATE = new BigDecimal("0.15");

    private static final BigDecimal INCOME_INCREASED = new BigDecimal("5000000");

    @Override
    public BigDecimal calculate(BigDecimal income) {
        if (INCOME_INCREASED.compareTo(income) <= 0) {
            return INCREASED_TAX_RATE.multiply(income).setScale(2, RoundingMode.HALF_UP);
        }
        return STANDARD_TAX_RATE.multiply(income).setScale(2, RoundingMode.HALF_UP);
    }
}

package ru.levelp.at.lesson12.design.patterns.strategy;

import java.math.BigDecimal;

public class TaxCalculatorProviderImpl implements TaxCalculatorProvider {

    private final TaxCalculator calculator;

    public TaxCalculatorProviderImpl(TaxCalculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public BigDecimal calculate(BigDecimal income) {
        return calculator.calculate(income);
    }
}

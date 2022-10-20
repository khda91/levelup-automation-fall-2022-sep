package ru.levelp.at.lesson12.design.patterns.strategy;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TuTaxCalculator implements TaxCalculator {

    private static final BigDecimal FIFTEEN_TAX_RATE = new BigDecimal("0.15");
    private static final BigDecimal TWENTY_TAX_RATE = new BigDecimal("0.20");
    private static final BigDecimal TWENTY_SEVEN_TAX_RATE = new BigDecimal("0.27");
    private static final BigDecimal THIRTY_FIFE_TAX_RATE = new BigDecimal("0.35");
    private static final BigDecimal FORTY_TAX_RATE = new BigDecimal("0.40");

    private static final BigDecimal FIFTEEN_LIMIT = new BigDecimal("32000");
    private static final BigDecimal TWENTY_LIMIT = new BigDecimal("70000");
    private static final BigDecimal TWENTY_SEVEN_LIMIT = new BigDecimal("250000");
    private static final BigDecimal FORTY_LIMIT = new BigDecimal("880000");

    @Override
    public BigDecimal calculate(BigDecimal income) {
        if (FIFTEEN_LIMIT.compareTo(income) <= 0 && TWENTY_LIMIT.compareTo(income) > 0) {
            return TWENTY_TAX_RATE.multiply(income).setScale(2, RoundingMode.HALF_UP);
        } else if (TWENTY_LIMIT.compareTo(income) <= 0 && TWENTY_SEVEN_LIMIT.compareTo(income) > 0) {
            return TWENTY_SEVEN_TAX_RATE.multiply(income).setScale(2, RoundingMode.HALF_UP);
        } else if (TWENTY_SEVEN_LIMIT.compareTo(income) <= 0 && FORTY_LIMIT.compareTo(income) > 0) {
            return THIRTY_FIFE_TAX_RATE.multiply(income).setScale(2, RoundingMode.HALF_UP);
        } else if (FORTY_TAX_RATE.compareTo(income) <= 0) {
            return FORTY_TAX_RATE.multiply(income).setScale(2, RoundingMode.HALF_UP);
        }

        return FIFTEEN_TAX_RATE.multiply(income).setScale(2, RoundingMode.HALF_UP);
    }
}

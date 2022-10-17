package ru.levelp.at.lesson02.git;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalculatorNegativeTest {

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Test
    void negativeTest() {
        assertThatThrownBy(() -> calculator.factorial(-1))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @AfterEach
    void tearDown() {
        calculator = null;
    }
}

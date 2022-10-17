package ru.levelp.at.lesson02.git;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Test
    void sumTest() {
        int sum = calculator.sum(2, 2);
        assertThat(sum).isEqualTo(4);
    }

    @Test
    void subtractTest() {
        int subtract = calculator.subtract(2, 3);
        assertThat(subtract).isEqualTo(-1);
    }

    @AfterEach
    void tearDown() {
        calculator = null;
    }
}

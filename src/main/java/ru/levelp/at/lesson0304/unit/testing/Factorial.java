package ru.levelp.at.lesson0304.unit.testing;

public class Factorial {

    public long calculate(long num) {
        if (num < 0) {
            throw new IllegalArgumentException("У отрицательного числа нельзя получить факториал!");
        }

        long result = 1;
        for (int i = 1; i <= num; i++) {
            result *= i;
        }
        return result;
    }
}

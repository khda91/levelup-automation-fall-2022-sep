package ru.levelp.at.lesson02.git;

public class Calculator {

    public int sum(int a, int b) {
        return a + b;
    }

    public double sum(double a, double b) {
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }

    public double subtract(double a, double b) {
        return a - b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    public double divide(double a, double b) {
        return a / b;
    }

    public long factorial(long a) {
        long res = 1;
        if (a < 0) {
            throw new IllegalArgumentException("Отрицательного факториала не бывает");
        }

        for (int i = 1; i <= a; i++) {
            res *= i;
        }

        return res;
    }

    public double sin(double d) {
        return Math.sin(d);
    }

    public int power(int a, int pow) {
        if (pow < 0) {
            throw new IllegalArgumentException("Степерь не может быть отрицательной");
        }

        int res = 1;
        for (int i = 0; i < pow; i++) {
            res *= a;
        }
        return res;
    }
}

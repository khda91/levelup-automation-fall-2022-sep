package ru.levelp.at.lesson02.git;

public class App {

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        System.out.println("Сумма");

        System.out.println("2 + 2 = " + calculator.sum(2, 2));
        System.out.println("100 + 100 = " + calculator.sum(100, 100));

        System.out.println("========");
        System.out.println();

        System.out.println("Вычитание");

        System.out.println("2 - 2 = " + calculator.subtract(2, 2));
        System.out.println("3 - 2 = " + calculator.subtract(3, 2));

        System.out.println("========");
        System.out.println();

        System.out.println("Умножение");

        System.out.println("2 * 2 = " + calculator.multiply(2, 2));
        System.out.println("4 * 4 = " + calculator.multiply(4, 4));

        System.out.println("========");
        System.out.println();
    }
}

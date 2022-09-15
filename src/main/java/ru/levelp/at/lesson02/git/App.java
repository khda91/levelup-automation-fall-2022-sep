package ru.levelp.at.lesson02.git;

public class App {

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        System.out.println("Сумма");

        System.out.println("2 + 2 = " + calculator.sum(2, 2));

        System.out.println("========");
        System.out.println();
        
        System.out.println("Вычитание");

        System.out.println("2 - 2 = " + calculator.subtract(2, 2));

        System.out.println("========");
        System.out.println();
    }
}

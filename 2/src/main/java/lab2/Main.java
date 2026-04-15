package lab2;

import java.util.Scanner;

/**
 * проверкв ExpressionEvaluator.
 */
public class Main {
    /**
     * считывает выражение из консоли и выводит результат
     *
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ExpressionEvaluator evaluator = new ExpressionEvaluator();

        System.out.print("Введите выражение: ");
        String expression = scanner.nextLine();

        try {
            double result = evaluator.evaluate(expression);
            System.out.println("Результат: " + result);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}

package lab3;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * сравнение производительности ArrayList и LinkedList
 */
public class Main {
    private static final int OPERATIONS = 100_000;

    /**
     * запускает все тесты и выводит таблицу результатов
     *
     * @param tester1 тестер первой коллекции
     * @param tester2 тестер второй коллекции
     */
    private static void runPerformanceTests(ListTester tester1, ListTester tester2) {
        System.out.printf("%-20s %-11s %-15s %-15s%n", "Method", "Operations",
                tester1.getCollectionName() + " (ms)", tester2.getCollectionName() + " (ms)");
        System.out.println("--------------------------------------------------------------");

        double timeAdd1 = tester1.testAdd();
        double timeAdd2 = tester2.testAdd();
        System.out.printf("%-20s %-11d %-15.2f %-15.2f%n", "add (end)", OPERATIONS, timeAdd1, timeAdd2);

        double timeAddRand1 = tester1.testAddRandomPoint();
        double timeAddRand2 = tester2.testAddRandomPoint();
        System.out.printf("%-20s %-11d %-15.2f %-15.2f%n", "add (random)", OPERATIONS, timeAddRand1, timeAddRand2);

        double timeGet1 = tester1.testGet();
        double timeGet2 = tester2.testGet();
        System.out.printf("%-20s %-11d %-15.2f %-15.2f%n", "get (random)", OPERATIONS, timeGet1, timeGet2);

        double timeRemove1 = tester1.testRemove();
        double timeRemove2 = tester2.testRemove();
        System.out.printf("%-20s %-11d %-15.2f %-15.2f%n", "remove (first)", OPERATIONS, timeRemove1, timeRemove2);

        double timeRemoveRand1 = tester1.testRemoveRandomPoint();
        double timeRemoveRand2 = tester2.testRemoveRandomPoint();
        System.out.printf("%-20s %-11d %-15.2f %-15.2f%n", "remove (random)", OPERATIONS, timeRemoveRand1, timeRemoveRand2);

        double timeContains1 = tester1.testContains();
        double timeContains2 = tester2.testContains();
        System.out.printf("%-20s %-11d %-15.2f %-15.2f%n", "contains", OPERATIONS, timeContains1, timeContains2);

        double timeSetRand1 = tester1.testSetRandomPoint();
        double timeSetRand2 = tester2.testSetRandomPoint();
        System.out.printf("%-20s %-11d %-15.2f %-15.2f%n", "set (random)", OPERATIONS, timeSetRand1, timeSetRand2);
    }

    public static void main(String[] args) {
        ListTester arrayTester = new ListTester(new ArrayList<>(), OPERATIONS);
        ListTester linkedTester = new ListTester(new LinkedList<>(), OPERATIONS);

        runPerformanceTests(arrayTester, linkedTester);
    }
}

package lab3;

import java.util.List;
import java.util.Random;

/**
 * обёртка для замера производительности операций над List
 */
public class ListTester {
    private final List<Integer> list;
    private final int operations;

    /**
     * создаёт тестер для указанного списка
     *
     * @param list       пустой список для тестирования
     * @param operations количество повторений каждой операции
     */
    public ListTester(List<Integer> list, int operations) {
        this.list = list;
        this.operations = operations;
    }

    /**
     * замеряет добавление элементов в конец списка
     *
     * @return время выполнения в миллисекундах
     */
    public double testAdd() {
        list.clear();
        long start = System.nanoTime();
        for (int i = 0; i < operations; i++) {
            list.add(i);
        }
        long end = System.nanoTime();
        return (end - start) / 1_000_000.0;
    }

    /**
     * замеряет чтение по случайному индексу
     *
     * @return время выполнения в миллисекундах
     */
    public double testGet() {
        list.clear();
        for (int i = 0; i < operations; i++) {
            list.add(i);
        }
        Random random = new Random();
        long start = System.nanoTime();
        for (int i = 0; i < operations; i++) {
            int index = random.nextInt(operations);
            list.get(index);
        }
        long end = System.nanoTime();
        return (end - start) / 1_000_000.0;
    }

    /**
     * замеряет удаление первого элемента
     *
     * @return время выполнения в миллисекундах
     */
    public double testRemove() {
        list.clear();
        for (int i = 0; i < operations; i++) {
            list.add(i);
        }
        long start = System.nanoTime();
        for (int i = 0; i < operations; i++) {
            list.remove(0);
        }
        long end = System.nanoTime();
        return (end - start) / 1_000_000.0;
    }

    /**
     * замеряет вставку в случайную позицию
     *
     * @return время выполнения в миллисекундах
     */
    public double testAddRandomPoint() {
        list.clear();
        for (int i = 0; i < operations; i++) {
            list.add(i);
        }
        Random random = new Random();
        long start = System.nanoTime();
        for (int i = 0; i < operations; i++) {
            int index = random.nextInt(list.size() + 1);
            list.add(index, i + operations);
        }
        long end = System.nanoTime();
        return (end - start) / 1_000_000.0;
    }

    /**
     * замеряет удаление из случайной позиции
     *
     * @return время выполнения в миллисекундах
     */
    public double testRemoveRandomPoint() {
        list.clear();
        for (int i = 0; i < operations; i++) {
            list.add(i);
        }
        Random random = new Random();
        long start = System.nanoTime();
        for (int i = 0; i < operations; i++) {
            if (!list.isEmpty()) {
                int index = random.nextInt(list.size());
                list.remove(index);
            }
        }
        long end = System.nanoTime();
        return (end - start) / 1_000_000.0;
    }

    /**
     * замеряет поиск элемента в списке
     *
     * @return время выполнения в миллисекундах
     */
    public double testContains() {
        list.clear();
        for (int i = 0; i < operations; i++) {
            list.add(i);
        }
        Random random = new Random();
        long start = System.nanoTime();
        for (int i = 0; i < operations; i++) {
            int value = random.nextInt(operations * 2);
            list.contains(value);
        }
        long end = System.nanoTime();
        return (end - start) / 1_000_000.0;
    }

    /**
     * замеряет замену элемента в случайной позиции
     *
     * @return время выполнения в миллисекундах
     */
    public double testSetRandomPoint() {
        list.clear();
        for (int i = 0; i < operations; i++) {
            list.add(i);
        }
        Random random = new Random();
        long start = System.nanoTime();
        for (int i = 0; i < operations; i++) {
            if (!list.isEmpty()) {
                int index = random.nextInt(list.size());
                list.set(index, i + operations);
            }
        }
        long end = System.nanoTime();
        return (end - start) / 1_000_000.0;
    }

    /**
     * возвращает имя класса коллекции для вывода
     *
     * @return простое имя класса списка
     */
    public String getCollectionName() {
        return list.getClass().getSimpleName();
    }
}

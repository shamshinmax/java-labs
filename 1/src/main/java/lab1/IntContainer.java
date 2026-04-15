package lab1;


public class IntContainer {
    private int[] data = new int[10];
    private int size;

    /**
     * добавляет элемент в конец контейнера
     *
     * @param value значение для добавления
     */
    public void add(int value) {
        if (size == data.length) {
            int[] temp = new int[data.length * 2];
            for (int i = 0; i < size; i++) {
                temp[i] = data[i];
            }
            data = temp;
        }
        data[size++] = value;
    }

    /**
     * возвращает элемент по индексу
     *
     * @param index индекс элемента
     * @return значение элемента
     * @throws IndexOutOfBoundsException если индекс некорректный
     */
    public int get(int index) {
        checkIndex(index);
        return data[index];
    }

    /**
     * удаляет элемент по индексу и возвращает его
     *
     * @param index индекс удаляемого элемента
     * @return удаленный элемент
     * @throws IndexOutOfBoundsException если индекс некорректный
     */
    public int remove(int index) {
        checkIndex(index);
        int value = data[index];
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        size--;
        return value;
    }

    /**
     * возвращает текущее количество элементов
     *
     * @return размер контейнера
     */
    public int size() {
        return size;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }
}

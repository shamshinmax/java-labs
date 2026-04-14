package lab1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class IntContainerTest {

    @Test
    void addAndGetWorkCorrectly() {
        IntContainer container = new IntContainer();
        container.add(10);
        container.add(20);
        container.add(30);

        assertEquals(10, container.get(0));
        assertEquals(20, container.get(1));
        assertEquals(30, container.get(2));
    }

    @Test
    void sizeChangesAfterAdd() {
        IntContainer container = new IntContainer();
        container.add(1);
        container.add(2);

        assertEquals(2, container.size());
    }

    @Test
    void removeReturnsValueAndShiftsElements() {
        IntContainer container = new IntContainer();
        container.add(5);
        container.add(15);
        container.add(25);

        int removed = container.remove(1);

        assertEquals(15, removed);
        assertEquals(2, container.size());
        assertEquals(25, container.get(1));
    }

    @Test
    void getThrowsForInvalidIndex() {
        IntContainer container = new IntContainer();
        container.add(42);

        assertThrows(IndexOutOfBoundsException.class, () -> container.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> container.get(1));
    }

    @Test
    void removeThrowsForInvalidIndex() {
        IntContainer container = new IntContainer();

        assertThrows(IndexOutOfBoundsException.class, () -> container.remove(0));
    }
}

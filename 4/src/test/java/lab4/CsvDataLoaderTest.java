package lab4;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class CsvDataLoaderTest {

    @Test
    void loadFromStringAndDivisionIdGeneration() throws IOException {
        CsvDataLoader loader = new CsvDataLoader();
        List<Employee> employees = loader.loadEmployees("testData.csv", false);

        assertEquals(4, employees.size());

        Employee emp1 = employees.get(0);
        assertEquals(1001L, emp1.getId());
        assertEquals("Ivan Ivanov", emp1.getName());
        assertEquals("Male", emp1.getGender());
        assertEquals(LocalDate.of(1990, 5, 15), emp1.getBirthDate());
        assertEquals(5000, emp1.getSalary());

        Subdivision divI1 = emp1.getDivision();
        Subdivision divJ = employees.get(1).getDivision();
        Subdivision divI2 = employees.get(2).getDivision();
        Subdivision divK = employees.get(3).getDivision();

        assertEquals(divI1.getId(), divI2.getId());
        assertSame(divI1, divI2);

        assertNotEquals(divI1.getId(), divJ.getId());
        assertNotEquals(divI1.getId(), divK.getId());
        assertNotEquals(divJ.getId(), divK.getId());

        assertEquals("I", divI1.getName());
        assertEquals("J", divJ.getName());
        assertEquals("K", divK.getName());
    }
}

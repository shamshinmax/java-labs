package lab4;

import java.time.LocalDate;
import java.util.Objects;

/**
 * сотрудник, загружаемый из csv
 */
public class Employee {
    private final long id;
    private final String name;
    private final String gender;
    private final LocalDate birthDate;
    private final Subdivision division;
    private final int salary;

    /**
     * создаёт сотрудника со всеми полями из csv
     *
     * @param id        идентификатор
     * @param name      имя
     * @param gender    пол
     * @param birthDate дата рождения
     * @param division  подразделение
     * @param salary    зарплата
     */
    public Employee(long id, String name, String gender, LocalDate birthDate, Subdivision division, int salary) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
        this.division = division;
        this.salary = salary;
    }

    /**
     * возвращает идентификатор сотрудника
     *
     * @return id
     */
    public long getId() {
        return id;
    }

    /**
     * возвращает имя сотрудника
     *
     * @return имя
     */
    public String getName() {
        return name;
    }

    /**
     * возвращает пол сотрудника
     *
     * @return пол
     */
    public String getGender() {
        return gender;
    }

    /**
     * возвращает дату рождения
     *
     * @return дата рождения
     */
    public LocalDate getBirthDate() {
        return birthDate;
    }

    /**
     * возвращает подразделение сотрудника
     *
     * @return подразделение
     */
    public Subdivision getDivision() {
        return division;
    }

    /**
     * возвращает зарплату
     *
     * @return зарплата
     */
    public int getSalary() {
        return salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Employee employee = (Employee) o;
        return id == employee.id
                && salary == employee.salary
                && Objects.equals(name, employee.name)
                && Objects.equals(gender, employee.gender)
                && Objects.equals(birthDate, employee.birthDate)
                && Objects.equals(division, employee.division);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, gender, birthDate, division, salary);
    }

    @Override
    public String toString() {
        return "Employee{id=" + id + ", name='" + name + "', gender='" + gender
                + "', birthDate=" + birthDate + ", division=" + division + ", salary=" + salary + "}";
    }
}

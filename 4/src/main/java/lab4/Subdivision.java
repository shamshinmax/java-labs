package lab4;

import java.util.Objects;

/**
 * подразделение сотрудника
 */
public class Subdivision {
    private final int id;
    private final String name;

    /**
     * создаёт подразделение с указанным id и именем
     *
     * @param id   идентификатор подразделения
     * @param name название подразделения
     */
    public Subdivision(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * возвращает идентификатор подразделения
     *
     * @return id подразделения
     */
    public int getId() {
        return id;
    }

    /**
     * возвращает название подразделения
     *
     * @return название
     */
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Subdivision that = (Subdivision) o;
        return id == that.id && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Subdivision{id=" + id + ", name='" + name + "'}";
    }
}

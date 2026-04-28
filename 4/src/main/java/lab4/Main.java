package lab4;

import java.util.List;

/**
 * демонстрация загрузки сотрудников из csv
 */
public class Main {

    public static void main(String[] args) {
        try {
            CsvDataLoader loader = new CsvDataLoader();
            List<Employee> employees = loader.loadEmployees("foreign_names.csv", true);
            System.out.println("Загружено сотрудников: " + employees.size());
            System.out.println("Первый сотрудник: " + employees.get(0));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package lab4;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * загружает сотрудников из csv-файла в resources
 */
public class CsvDataLoader {

    /**
     * читает csv и создаёт список сотрудников
     *
     * @param resourceName имя файла в resources
     * @param skipHeader   пропускать ли первую строку с заголовком
     * @return список сотрудников
     * @throws IOException если файл не найден или не удалось прочитать
     */
    public List<Employee> loadEmployees(String resourceName, boolean skipHeader) throws IOException {
        List<Employee> employees = new ArrayList<>();
        Map<String, Subdivision> divisionMap = new HashMap<>();
        AtomicInteger divisionIdCounter = new AtomicInteger(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        CSVParser parser = new CSVParserBuilder()
                .withSeparator(';')
                .withIgnoreQuotations(true)
                .build();

        try (InputStream is = getClass().getClassLoader().getResourceAsStream(resourceName);
             CSVReader csvReader = new CSVReaderBuilder(new InputStreamReader(is))
                     .withSkipLines(skipHeader ? 1 : 0)
                     .withCSVParser(parser)
                     .build()) {

            String[] parts;
            while ((parts = csvReader.readNext()) != null) {
                if (parts.length < 6) {
                    continue;
                }

                long empId = Long.parseLong(parts[0].trim());
                String name = parts[1].trim();
                String gender = parts[2].trim();
                LocalDate birthDate = LocalDate.parse(parts[3].trim(), formatter);
                String divName = parts[4].trim();
                int salary = Integer.parseInt(parts[5].trim());

                Subdivision division = divisionMap.computeIfAbsent(divName,
                        k -> new Subdivision(divisionIdCounter.getAndIncrement(), k));

                employees.add(new Employee(empId, name, gender, birthDate, division, salary));
            }
        } catch (CsvValidationException e) {
            throw new IOException("Ошибка валидации CSV при чтении " + resourceName, e);
        }

        return employees;
    }
}

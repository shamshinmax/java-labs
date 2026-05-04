package lab5;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Properties;

/**
 * простой dependency injector через reflection и properties-файл
 */
public class Injector {
    private final Properties properties = new Properties();

    /**
     * загружает конфигурацию из injector.properties
     *
     * @throws RuntimeException если файл не найден или не удалось прочитать
     */
    public Injector() {
        try (FileInputStream fis = new FileInputStream("injector.properties")) {
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Не удалось прочитать файл конфигурации", e);
        }
    }

    /**
     * внедряет зависимости в поля, помеченные @AutoInjectable
     *
     * @param obj объект для внедрения зависимостей
     * @param <T> тип объекта
     * @return тот же объект с заполненными полями
     * @throws RuntimeException если внедрение не удалось
     */
    public <T> T inject(T obj) {
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            if (!field.isAnnotationPresent(AutoInjectable.class)) {
                continue;
            }

            String interfaceName = field.getType().getName();
            String implClassName = properties.getProperty(interfaceName);

            if (implClassName == null || implClassName.trim().isEmpty()) {
                throw new RuntimeException("Не найдена реализация для интерфейса: " + interfaceName);
            }

            try {
                Class<?> implClass = Class.forName(implClassName.trim());
                var constructor = implClass.getDeclaredConstructor();
                constructor.setAccessible(true);
                Object implInstance = constructor.newInstance();

                field.setAccessible(true);
                field.set(obj, implInstance);
            } catch (Exception e) {
                throw new RuntimeException("Не удалось внедрить поле " + field.getName()
                        + " типа " + interfaceName, e);
            }
        }

        return obj;
    }
}

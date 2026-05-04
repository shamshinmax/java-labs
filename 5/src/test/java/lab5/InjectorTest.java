package lab5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class InjectorTest {

    private Injector injector;

    @BeforeEach
    void setUp() throws IOException {
        try (FileWriter writer = new FileWriter("injector.properties")) {
            writer.write("lab5.SomeInterface=lab5.SomeImpl\n");
            writer.write("lab5.SomeOtherInterface=lab5.SODoer\n");
        }
        injector = new Injector();
    }

    @Test
    void shouldInjectDependenciesAndCallMethods() {
        SomeBean bean = injector.inject(new SomeBean());
        assertNotNull(bean);
        bean.foo();
    }

    @Test
    void shouldInjectDifferentImplementation() throws IOException {
        try (FileWriter writer = new FileWriter("injector.properties")) {
            writer.write("lab5.SomeInterface=lab5.OtherImpl\n");
            writer.write("lab5.SomeOtherInterface=lab5.SODoer\n");
        }
        injector = new Injector();

        SomeBean bean = injector.inject(new SomeBean());
        bean.foo();
    }

    @Test
    void shouldThrowExceptionWhenNoImplementationFound() throws IOException {
        try (FileWriter writer = new FileWriter("injector.properties")) {
            writer.write("lab5.SomeOtherInterface=lab5.SODoer\n");
        }
        injector = new Injector();

        SomeBean bean = new SomeBean();
        assertThrows(RuntimeException.class, () -> injector.inject(bean));
    }
}

interface SomeInterface {
    void doSomething();
}

interface SomeOtherInterface {
    void doSomeOther();
}

class SomeImpl implements SomeInterface {
    @Override
    public void doSomething() {
        System.out.print("A");
    }
}

class OtherImpl implements SomeInterface {
    @Override
    public void doSomething() {
        System.out.print("B");
    }
}

class SODoer implements SomeOtherInterface {
    @Override
    public void doSomeOther() {
        System.out.print("C");
    }
}

class SomeBean {
    @AutoInjectable
    private SomeInterface field1;

    @AutoInjectable
    private SomeOtherInterface field2;

    public void foo() {
        field1.doSomething();
        field2.doSomeOther();
    }
}

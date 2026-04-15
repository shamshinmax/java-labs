package lab1;

/**
 * демонстрация работы IntContainer
 */
public class Main {
    public static void main(String[] args) {
        
        IntContainer container = new IntContainer();


        container.add(10);
        container.add(20);
        container.add(30);


        System.out.println("Второй: " + container.get(1));
        System.out.println("Удаленный: " + container.remove(0));
        System.out.println("Размер: " + container.size());
    }
}

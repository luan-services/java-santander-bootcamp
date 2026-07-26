import java.util.Scanner;
import test.TestClass;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

        Scanner scanner = new Scanner(System.in);

        String name = scanner.next();
        int age = scanner.nextInt();

        System.out.println(name + age);
        scanner.close();

        TestClass t = new TestClass();
        t.runTest(67);
    }
}

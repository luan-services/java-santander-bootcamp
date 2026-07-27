import java.util.Scanner;

import class_one.QuestionsClass;
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
        TestClass.runTest(67); /* since 'run test' is a static method, we do not need a instance of TestClass to call it */

        QuestionsClass.questionOne("Luan", 1997);
        QuestionsClass.questionTwo(14);
        QuestionsClass.questionThree(5, 12);
        QuestionsClass.questionFour(19, 24);
    }
}

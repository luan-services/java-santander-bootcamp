package class_two;

public class QuestionsClass {
    
    public static void generateMultiples(int number) {
        System.out.printf("Multiples of %d:\n");
        for (int i = 1; i < 11; i ++) {
            System.out.printf("%d:\n %d", i, i * number);
        }
    }
}

package class_one;

public class QuestionsClass {

    public static void questionOne(String name, int year) {
        int bornAt = 2026 - year;
        System.out.printf("Hello, %s. You are %d years old\n", name, bornAt);
    }

    public static void questionTwo(int side) {
        int area = side * side;
        System.out.printf("The square area is %d\n", area);
    }

    public static void questionThree(int base, int height) {
        int area = base * height;
        System.out.printf("The rectangle area is %d\n", area);
    }

    public static void questionFour(int ageOne, int ageTwo) {
        int difference = Math.abs(ageOne - ageTwo);
        System.out.printf("The difference between age is %d\n", difference);
    }
    
    public static void main(String[] args) {
        
    }
}
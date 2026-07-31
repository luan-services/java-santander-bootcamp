
public class Person {
    
    private int age;
    private String name;

    public Person(String angelNumber) { /* a 'blank' constructor allows the user to instance an object without setting its values,
        (except for 'final' attributes), without this blank constructor, user would need to also set name and age when instancing the
        object */
        this.angelNumber = angelNumber;
    }
    
    public Person(String name, int age, String angelNumber) {
        this.name = name;
        this.age = age;
        this.angelNumber = angelNumber;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    public void setName(String name) {
         this.name = name;
    }

    public void setAge(int age) {
         this.age = age;
    }

    public void incrementAge() { /* alternative function that lets the user increase age instead of setting */
         this.age = this.age + 1;
    }

    private static String sharedValue; /* static values are shared across every single instance of the class, they belong to the class
    and can be acessed even without a instance of person */

    public static String getSharedValue() {
        return sharedValue;
    }

    public static void setSharedValue(String sharedValue) {
        Person.sharedValue = sharedValue;
    }
    
    private final String angelNumber; /* a final value is a value that can be assigned only ONCE, it is like a const on JavaScript, 
    but since it is inside a class, it has some rules:
        it cannot be changed by a setter.
        it must be assigned only ONCE on instance time, i.e: it must be assigned on every constructor of the class. */

    public String getAgelNumber() {
        return angelNumber;
    }
}

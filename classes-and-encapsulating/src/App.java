public class App {
    public static void main(String[] args) throws Exception {

        Person user = new Person("WE32-CX4952");
        user.setName("João");
        user.setAge(18);
        System.out.println("Hello, World!");
        System.out.println(user.getName() + " " + user.getAge());

        Person.setSharedValue("value");
        user.setSharedValue("override");

        System.out.println(user.getSharedValue());
    }
}

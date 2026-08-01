public class App {
    public static void main(String[] args) throws Exception {
        Employee employee = new Employee();
        Manager manager = new Manager();
        Salesman salesman = new Salesman();
        
        manager.setName("Luan");

        Employee dude = new Manager(); /* since manager extends an employee, you can type cast a employee and instance a manager, 
        this is called polymorphism */

        /* dude.setLogin("A") - X WON'T WORK because employee has no login attribute  
        
        even tho a manager object is created, the dude variable won't let you touch manager attributes because Employee doesn't know 
        those fields exist. 

        polymorphism is used to treat different classes as a group.

        simple put, if you want to update "code" that is an attribute from Employee, Manager and Salesman on three different objects,
        without polymorphism you'd need to cast them all and manually update, but with it you can simple make a array of Employee type
        but instancing Manager and Salesman:
        */

        Employee[] staff = { new Manager(), new Salesman(), new Employee() };

        for (Employee emp : staff) {
            emp.setCode("NULL"); 
        }
        /* you can put the object into an Employee variable to treat it generally, and then later re-cast it back to a Manager whenever 
        you need to use its Manager-specific attributes or methods: */

        if (staff[0] instanceof Manager) {
            Manager m = (Manager) staff[0]; /* recast maanger as a manager to access its values */
            System.out.println(m instanceof Employee); /* true  */
            System.out.println(employee instanceof Manager); /* false  */
            /* System.out.println(m instanceof Salesman); can't do it because they are not related */
        }

        System.out.println("Hello, World!");

    }
}

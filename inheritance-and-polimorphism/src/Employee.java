public /* abstract */ /* final */ class Employee {
    /* when you add abstract there you don't allow anyone to instance an Employee (new Employee()), this is useful
    for classes that will be extended to bigger classes */

    /* when you add final to the class you don't let anyone extend it, i.e no other classes can inherit Employee class */
    
    private String code;
    private String name;
    private String address;
    private int age;
    private double salary;

    public String getCode() {
        return code;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getSalary() {
        return salary;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}

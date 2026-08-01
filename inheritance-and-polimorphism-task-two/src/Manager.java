public class Manager extends User {

    public Manager(String name, String email, String password) {
        super(name, email, password, true);
    }

    public void generateFinancialReport() {
        System.out.println("Generating financial report...");
    }

    public void consultSales() {
        System.out.println("Consulting sales...");
    }
}
public class App {

    public static void main(String[] args) {

        Manager manager = new Manager("Alice", "alice@email.com", "123");
        Salesperson salesperson = new Salesperson("Bob", "bob@email.com", "456");
        Cashier cashier = new Cashier("Carol", "carol@email.com", "789");

        manager.login();
        manager.generateFinancialReport();
        manager.consultSales();
        manager.logoff();

        System.out.println();

        salesperson.login();
        salesperson.makeSale();
        salesperson.makeSale();
        salesperson.consultSales();
        salesperson.logoff();

        System.out.println();

        cashier.login();
        cashier.receivePayment(100);
        cashier.receivePayment(50);
        cashier.closeCashRegister();
        cashier.logoff();
    }
}
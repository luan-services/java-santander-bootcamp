public class Cashier extends User {

    private double cashAmount;

    public Cashier(String name, String email, String password) {
        super(name, email, password, false);
        this.cashAmount = 0;
    }

    public double getCashAmount() {
        return cashAmount;
    }

    public void receivePayment(double amount) {
        cashAmount += amount;
    }

    public void closeCashRegister() {
        System.out.println("Cash register closed.");
        System.out.println("Total cash: $" + cashAmount);
    }
}
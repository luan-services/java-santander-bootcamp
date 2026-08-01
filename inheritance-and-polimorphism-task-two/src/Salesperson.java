public class Salesperson extends User {

    private int salesCount;

    public Salesperson(String name, String email, String password) {
        super(name, email, password, false);
        this.salesCount = 0;
    }

    public int getSalesCount() {
        return salesCount;
    }

    public void makeSale() {
        salesCount++;
    }

    public void consultSales() {
        System.out.println("Sales made: " + salesCount);
    }
}
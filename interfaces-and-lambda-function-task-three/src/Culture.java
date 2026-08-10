public class Culture implements Taxable {

    private double price;

    public Culture(double price) {
        this.price = price;
    }

    @Override
    public double calculateTax() {
        return price * 0.04;
    }
}

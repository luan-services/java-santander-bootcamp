public class Clothing implements Taxable {

    private double price;

    public Clothing(double price) {
        this.price = price;
    }

    @Override
    public double calculateTax() {
        return price * 0.025;
    }
}

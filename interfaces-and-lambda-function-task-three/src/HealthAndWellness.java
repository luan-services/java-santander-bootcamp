public class HealthAndWellness implements Taxable {

    private double price;

    public HealthAndWellness(double price) {
        this.price = price;
    }

    @Override
    public double calculateTax() {
        return price * 0.015;
    }
}

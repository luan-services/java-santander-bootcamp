public class Food implements Taxable {

    private double price;

    public Food(double price) {
        this.price = price;
    }

    @Override
    public double calculateTax() {
        return price * 0.01;
    }
}

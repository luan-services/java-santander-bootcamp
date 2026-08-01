public class FamilyTicket extends Ticket {
    private int numberOfPeople;

    public FamilyTicket(double price, String movieName, boolean dubbed, int numberOfPeople) {
        super(price, movieName, dubbed);
        this.numberOfPeople = numberOfPeople;
    }

    @Override
    public double getRealPrice() {
        double total = price * numberOfPeople;

        if (numberOfPeople > 3) {
            total *= 0.95; // 5% discount
        }

        return total;
    }
}
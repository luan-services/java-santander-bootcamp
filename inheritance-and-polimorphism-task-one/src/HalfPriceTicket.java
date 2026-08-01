public class HalfPriceTicket extends Ticket {

    public HalfPriceTicket(double price, String movieName, boolean dubbed) {
        super(price, movieName, dubbed);
    }

    @Override
    public double getRealPrice() {
        return price / 2;
    }
}
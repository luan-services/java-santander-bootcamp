public class Ticket {
    protected double price;
    protected String movieName;
    protected boolean dubbed;

    public Ticket(double price, String movieName, boolean dubbed) {
        this.price = price;
        this.movieName = movieName;
        this.dubbed = dubbed;
    }

    public double getRealPrice() {
        return price;
    }

    public String getMovieName() {
        return movieName;
    }

    public String getLanguage() {
        return dubbed ? "Dubbed" : "Subtitled";
    }
}
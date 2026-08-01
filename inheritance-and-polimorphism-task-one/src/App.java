public class App {
    public static void main(String[] args) {

        Ticket ticket = new Ticket(40.0, "Superman", true);
        HalfPriceTicket halfTicket = new HalfPriceTicket(40.0, "Superman", false);
        FamilyTicket family1 = new FamilyTicket(40.0, "Superman", true, 2);
        FamilyTicket family2 = new FamilyTicket(40.0, "Superman", false, 5);

        System.out.println("Regular ticket: $" + ticket.getRealPrice());
        System.out.println("Half-price ticket: $" + halfTicket.getRealPrice());
        System.out.println("Family ticket (2 people): $" + family1.getRealPrice());
        System.out.println("Family ticket (5 people): $" + family2.getRealPrice());
    }
}
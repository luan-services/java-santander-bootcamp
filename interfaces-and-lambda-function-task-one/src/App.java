public class App {

    public static void main(String[] args) {
        String message = "Aproveite nossa promoção especial!";

        MessageService sms = new SmsService();
        MessageService email = new EmailService();
        MessageService socialMedia = new SocialMediaService();
        MessageService whatsapp = new WhatsAppService();

        sms.sendMessage(message);
        email.sendMessage(message);
        socialMedia.sendMessage(message);
        whatsapp.sendMessage(message);
    }
}

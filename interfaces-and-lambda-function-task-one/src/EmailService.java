public class EmailService implements MessageService {

    @Override
    public void sendMessage(String message) {
        System.out.println("E-mail enviado: " + message);
    }
}

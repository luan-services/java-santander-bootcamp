public class SmsService implements MessageService {

    @Override
    public void sendMessage(String message) {
        System.out.println("SMS enviado: " + message);
    }
}

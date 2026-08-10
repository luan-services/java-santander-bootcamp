public class WhatsAppService implements MessageService {

    @Override
    public void sendMessage(String message) {
        System.out.println("WhatsApp enviado: " + message);
    }
}

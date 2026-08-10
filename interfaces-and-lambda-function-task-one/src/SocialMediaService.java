public class SocialMediaService implements MessageService {

    @Override
    public void sendMessage(String message) {
        System.out.println("Mensagem enviada nas redes sociais: " + message);
    }
}

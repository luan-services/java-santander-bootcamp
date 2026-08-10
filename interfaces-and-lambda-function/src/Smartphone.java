public class Smartphone implements VideoPlayer { 
    /* since we implemented our VideoPlayer interface, we must declare all its methods and implement it */
    @Override
    public void playMusic() {
        System.out.println("Playing 'Happy birthday to you'");
    }

    @Override
    public void pauseMusic() {
        System.out.println("Pausing 'Happy birthday to you'");

    }

    @Override
    public void stopMusic() {
        System.out.println("Stopping 'Happy birthday to you'");
    }

    @Override
    public void playVideo() {
        System.out.println("Playing 'Minecraft awesome montage 67'");
    }

    @Override
    public void pauseVideo() {
        System.out.println("Pausing 'Minecraft awesome montage 67'");
    }

    @Override
    public void stopVideo() {
        System.out.println("Stopping 'Minecraft awesome montage 67'");
    }
}
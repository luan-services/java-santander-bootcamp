public record MusicBox(String music, boolean isPaused) implements MusicPlayer {
    /* records are not able to extend classes, they are plain objects, but they can implements interfaces to inherit their contracts */

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
}
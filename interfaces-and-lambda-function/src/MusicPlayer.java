public interface MusicPlayer {

    String music = "Happy birthday to you"; /* an attribute declared inside a interface is a constant, ie: it is public, static and final */

    void playMusic(); /* declaring methods here implies that every class that implements 'MusicPlayer' must also implements those methods */

    void pauseMusic();

    void stopMusic();
}
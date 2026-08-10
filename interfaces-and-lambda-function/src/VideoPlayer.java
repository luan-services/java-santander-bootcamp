public interface VideoPlayer extends MusicPlayer /* a interface can extend another interface and get its values */ {
    
    void playVideo(); /* declaring methods here implies that every class that implements 'MusicPlayer' must also implements those methods */

    void pauseVideo();

    void stopVideo();
}
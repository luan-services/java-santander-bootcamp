public class App {

    public static void main(String[] args) throws Exception {
        String song = MusicPlayer.music; /* receives the value of the constant at the interface */

        var musicPlayer = new MusicPlayer() { /* you can instance a object from an interface, but since its methods are not implemented, you must do it here, 
            this is called anonymous class and it is not the ideal use of interfaces, you cannot reuse this code anywhere else */
            @Override
            public void playMusic() {
                System.out.println("Playing 'Happy birthday to you'");
            }

            @Override
            public void pauseMusic() {

            }

            @Override
            public void stopMusic() {

            }
        };

        musicPlayer.playMusic();

        MusicPlayer musicBox = new MusicBox(); /* since MusicBox class implements the MusicPlayer interface, you are allowed to instance using the interface, but can only use
        the MusicPlayer methods */
        MusicBox musicBoxTwo = new MusicBox(); /* you can also use its own class, having every method from it */
        musicBox.playMusic();
        musicBox.pauseMusic();


        /* LAMBDA */

        List<User> users = List.of(new User("Maria", 21), new User("José", 26), new User("Rex", 12));
        users.forEach(user -> System.out.println(user)); /* this is the way we get every user from a list */
        users.forEach(System.out::println); /* this does the same but shorter, this is a lambda expression */
    }
}

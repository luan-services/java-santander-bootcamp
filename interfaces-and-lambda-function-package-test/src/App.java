import keyword.*;

public class App {

    public static void main(String[] args) throws Exception {
        /* Client cliente = new Client(); cannot instance client here because it is on a different package "keyword" and the class is not set as public */
        People pessoa = new People(); /* can instace people here because it is set as public even tho it is on a different package "keyword" */
    }
}

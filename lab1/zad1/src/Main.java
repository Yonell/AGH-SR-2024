public class Main {
    public static void main(String[] args) throws InterruptedException {
        ServerThread server = new ServerThread();
        ClientThread client = new ClientThread();
        server.start();
        client.start();
        server.join();
        client.join();

        return;

    }
}
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Starting server...");
        Server server = new Server();
        server.start();
        System.out.println("Server started, waiting for clients...");
        server.join();
        return;
    }
}
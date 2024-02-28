import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Thread server = new ServerThread();
        Thread client = new ClientThread();
        server.start();
        Thread.sleep(500);
        client.start();
        server.join();
        client.join();

        return;
    }
}
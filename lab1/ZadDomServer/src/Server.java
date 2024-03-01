import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server extends Thread{
    private final MessageManager manager = new MessageManager();
    private final ServerSocket serverSocket;
    private int lastId = 0;
    private final List<Client> clients = new ArrayList<>();
    public Server() throws IOException {
        System.out.println("Acquiring port...");
        serverSocket = new ServerSocket(12345);
    }

    @Override
    public void run() {
        manager.start();
        while(true){
            try {
                Socket clientSocket = serverSocket.accept();
                Client newClient = new Client(lastId,manager,clientSocket);
                clients.add(newClient);
                newClient.start();
                System.out.println("New client: " + lastId);
                lastId++;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

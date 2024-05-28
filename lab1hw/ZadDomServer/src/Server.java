import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server extends Thread{
    private final MessageManager manager = new MessageManager();
    private final MessageManagerUDP managerUDP;
    private final ServerSocket serverSocketTCP;
    private final DatagramSocket serverSocketUDP;
    private int lastId = 0;
    private final List<Client> clients = new ArrayList<>();
    public Server() throws IOException {
        System.out.println("Acquiring port for TCP...");
        serverSocketTCP = new ServerSocket(12345);
        System.out.println("Acquiring port for UDP...");
        serverSocketUDP = new DatagramSocket(12345);
        managerUDP = new MessageManagerUDP(serverSocketUDP);
    }

    @Override
    public void run() {
        manager.start();
        managerUDP.start();
        while(true){
            try {
                Socket clientSocket = serverSocketTCP.accept();
                clientSocket.getOutputStream().write(lastId);
                Client newClient = new Client(lastId,manager,clientSocket);
                managerUDP.add_client(newClient);
                newClient.start();
                System.out.println("New client: " + lastId);
                lastId++;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

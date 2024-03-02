import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;
import java.util.List;

public class MessageManagerUDP extends Thread {
    private DatagramSocket socket;
    private List<Client> clients = new ArrayList<>();
    public MessageManagerUDP(DatagramSocket socket){
        this.socket = socket;
    }

    public void add_client(Client newClient){
        clients.add(newClient);
    }

    public void run(){
        while(true){
            byte[] bytes = new byte[1025];
            DatagramPacket packet = new DatagramPacket(bytes, 1025);
            try {
                socket.receive(packet);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            int authorID = packet.getData()[packet.getOffset()];
            String message = new String(packet.getData(), packet.getOffset()+1, packet.getLength()-1);
            System.out.println("Client " + authorID + " sent a message: " + message);
            message = authorID + ": " + message;
            for (Client client:
                 clients) {
                DatagramPacket packet1 = new DatagramPacket(message.getBytes(), message.getBytes().length,
                        client.getAddress(), client.getPortUDP());
                try {
                    socket.send(packet1);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}

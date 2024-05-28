import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.Socket;

public class Client extends Thread implements NewMessageObserver{
    private final int id;
    private final MessageManager manager;
    private final Socket clientSocket;
    private final int portUDP;

    private final InetAddress address;
    private final PrintWriter out;
    private final BufferedReader in;

    Client(int id, MessageManager manager, Socket clientSocket) throws IOException {
        this.id = id;
        this.manager = manager;
        manager.addToNewMessageObservers(this);
        this.clientSocket = clientSocket;
        this.out = new PrintWriter(clientSocket.getOutputStream(), true);
        this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String stringPort = in.readLine();
        this.portUDP = Integer.parseInt(stringPort);

        this.address = clientSocket.getInetAddress();
    }

    @Override
    public void newMessageNotify(Message message) {
        String msg = new String(message.getBytes());
        out.println(message.getAuthorId() + ": " + msg);
        out.flush();
    }

    public void run(){
        String msg;
        while(true){
            msg = null;
            try {
                msg = in.readLine();
            } catch (IOException e) {
                deinit();
                return;
            }
            if (msg == null) {
                deinit();
                return;
            }
            this.manager.addMessage(new Message(this.id, "text", true, msg.getBytes()));
        }
    }

    public int getPortUDP() {
        return portUDP;
    }

    public InetAddress getAddress() {
        return address;
    }

    public void deinit(){
        manager.removeObserver(this);
        try {
            clientSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Client " + id + " disconnected");
    }

}

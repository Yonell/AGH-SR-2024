import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.nio.ByteBuffer;
import java.util.ArrayDeque;
import java.util.Queue;

public class Client extends Thread{
    static Socket clientSocket = null;
    Queue<String> messageQueue = new ArrayDeque<>();
    static DatagramSocket clientSocketUDP = null;
    static PrintWriter out = null;
    static BufferedReader in = null;
    private static int clientID;

    public void run(){

        int localport;
        try {
            clientSocket = new Socket("localhost", 12345);
            clientSocketUDP = new DatagramSocket();
            localport = clientSocketUDP.getLocalPort();
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            clientID = clientSocket.getInputStream().read();
            out.println(String.valueOf(localport));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        messageWaiter waiterTCP = new messageWaiter(this, in);
        messageWaiter waiterUDP = new messageWaiter(this, clientSocketUDP);
        waiterUDP.start();
        waiterTCP.start();
        int command;
        while(true){
            System.out.print("Command: ");
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(System.in));
            try {
                command = System.in.read();
                System.in.skip(Long.MAX_VALUE);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String msg;
            switch (command){
                case 84:
                case 116:
                    try {
                        msg = reader.readLine();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    out.println(msg);
                    out.flush();
                    break;
                case 82:
                case 114:
                    while(messageQueue.size() != 0){
                        System.out.println(messageQueue.remove());
                    }
                    break;
                case 85:
                case 117:
                    String[] messages = new String[5];
                    messages[0] = "  |\\_/|";
                    messages[1] = " / @ @ \\";
                    messages[2] = "( > º < )";
                    messages[3] = " `>>x<<´";
                    messages[4] = " /  O  \\";
                    for (String message: messages) {
                        byte[] buf2 = new byte[message.getBytes().length+1];
                        buf2[0] = (byte) clientID;
                        System.arraycopy(message.getBytes(), 0, buf2, 1, message.getBytes().length);
                        DatagramPacket toSend = null;
                        try {
                            toSend = new DatagramPacket(buf2, message.getBytes().length+1, InetAddress.getByName("localhost"), 12345);
                        } catch (UnknownHostException e) {
                            throw new RuntimeException(e);
                        }
                        try {
                            clientSocketUDP.send(toSend);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
            }
        }
    }
    public void add_message(String message){
        messageQueue.add(message);
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Client extends Thread{
    static Socket clientSocketTCP = null;
    Queue<String> messageQueue = new ArrayDeque<>();
    static DatagramSocket clientSocketUDP = null;
    static PrintWriter out = null;
    static BufferedReader in = null;
    private Lock messageQueueLock = new ReentrantLock(true);
    private messageWaiter waiterTCP;
    private messageWaiter waiterUDP;

    public void run(){

        //initial communication
        int localport;
        int clientID;
        try {
            clientSocketTCP = new Socket("localhost", 12345);
            clientSocketUDP = new DatagramSocket();
            localport = clientSocketUDP.getLocalPort();
            out = new PrintWriter(clientSocketTCP.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocketTCP.getInputStream()));
            clientID = clientSocketTCP.getInputStream().read();
            out.println(String.valueOf(localport));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //messageWaiters initialization
        waiterTCP = new messageWaiter(this, in);
        waiterUDP = new messageWaiter(this, clientSocketUDP);
        waiterUDP.start();
        waiterTCP.start();

        //main loop
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
                        System.out.print("Enter message: ");
                        msg = reader.readLine();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    out.println(msg);
                    out.flush();
                    break;
                case 82:
                case 114:
                    messageQueueLock.lock();
                    while(messageQueue.size() != 0){
                        System.out.println(messageQueue.remove());
                    }
                    messageQueueLock.unlock();
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
                        DatagramPacket toSend;
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
                case 67:
                case 99:
                    deinit();
                    return;
            }
        }
    }
    public void add_message(String message){
        messageQueueLock.lock();
        messageQueue.add(message);
        messageQueueLock.unlock();
    }
    private void deinit(){
        try {
            clientSocketTCP.close();
            clientSocketUDP.close();
            waiterUDP.interrupt();
            waiterTCP.interrupt();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

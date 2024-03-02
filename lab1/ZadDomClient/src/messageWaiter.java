import java.io.BufferedReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class messageWaiter extends Thread{
    private Client parent;
    private boolean isTCP;
    private BufferedReader in = null;
    private DatagramSocket socket = null;

    public messageWaiter(Client parent, BufferedReader in){
        this.parent = parent;
        this.isTCP = true;
        this.in = in;
    }

    public messageWaiter(Client parent, DatagramSocket socket){
        this.parent = parent;
        this.isTCP = false;
        this.socket = socket;
    }


    public void run() {
        if(isTCP){
            String msg;
            while(true) {
                try {
                    msg = in.readLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                parent.add_message(msg);
            }
        }else{
            String msg;
            byte[] buf = new byte[1024];
            DatagramPacket received = new DatagramPacket(buf, 1024);
            while(true) {
                try {
                    socket.receive(received);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                msg = new String(received.getData(), received.getOffset(), received.getLength());
                parent.add_message(msg);
            }
        }
    }
}

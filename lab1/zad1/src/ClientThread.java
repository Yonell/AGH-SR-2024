import javax.xml.crypto.Data;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClientThread extends Thread{
    public void run() {
        try (DatagramSocket socket = new DatagramSocket(9877)) {

            InetAddress address = InetAddress.getByName("localhost");
            byte[] sendBuffer = "Ping".getBytes();
            DatagramPacket sendPacket =
                    new DatagramPacket(sendBuffer, sendBuffer.length, address, 9876);
            socket.send(sendPacket);

            byte[] receiveBuffer = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
            socket.receive(receivePacket);
            String msg = new String(receivePacket.getData(), receivePacket.getOffset(), receivePacket.getLength());
            System.out.println("received msg: " + msg);

        } catch (Exception e) {
            e.printStackTrace();
        }
        this.interrupt();
    }
}

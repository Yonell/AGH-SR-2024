import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ServerThread extends Thread{
    public void run(){
        try (DatagramSocket socket = new DatagramSocket(9876)) {
            byte[] receiveBuffer = new byte[1024];
            while (true) {
                DatagramPacket receivePacket =
                        new DatagramPacket(receiveBuffer, receiveBuffer.length);
                socket.receive(receivePacket);
                String msg = new String(receivePacket.getData(), receivePacket.getOffset(), receivePacket.getLength());
                System.out.println("received msg: " + msg);


                InetAddress address = InetAddress.getByName("localhost");
                byte[] sendBuffer = "Pong".getBytes();
                DatagramPacket sendPacket =
                        new DatagramPacket(sendBuffer, sendBuffer.length, address, 9877);
                socket.send(sendPacket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.interrupt();
    }
}

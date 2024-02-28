import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class Main {
    public static void main(String[] args) {
        DatagramSocket socket = null;
        ByteBuffer byteBuffer = ByteBuffer.allocate(4);
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        try {
            socket = new DatagramSocket(9876);
            byte[] receiveBuffer = new byte[4];
            while(true) {
                DatagramPacket receivePacket =
                        new DatagramPacket(receiveBuffer, receiveBuffer.length);
                socket.receive(receivePacket);
                byteBuffer.put(receivePacket.getData(), receivePacket.getOffset(), receivePacket.getLength());
                byteBuffer.rewind();
                Integer integer = byteBuffer.getInt();
                System.out.println("Received number: " + integer);

                integer++;
                byteBuffer.rewind();
                byteBuffer.putInt(integer);
                byteBuffer.rewind();
                byte[] sendBuffer = byteBuffer.array();
                DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, InetAddress.getByName("localhost"), 9877);
                socket.send(sendPacket);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null) socket.close();
        }
    }
}
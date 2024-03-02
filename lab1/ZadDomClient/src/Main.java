import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public  class Main {

    public Main() throws IOException {
    }

    public static void main(String[] args) throws InterruptedException {
        Client client = new Client();
        client.start();
        client.join();
    }
}
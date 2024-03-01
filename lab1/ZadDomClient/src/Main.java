import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Main {
    static Socket clientSocket = null;
    static PrintWriter out = null;
    static BufferedReader in = null;

    public Main() throws IOException {
    }

    public static void main(String[] args) throws IOException {
        clientSocket = new Socket("localhost", 12345);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        int command;
        while(true){
            System.out.print("Command: ");
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(System.in));
            command = System.in.read();
            System.in.skip(Long.MAX_VALUE);
            String msg;
            switch (command){
                case 84:
                case 116:
                    msg = reader.readLine();
                    out.println(msg);
                    out.flush();
                    break;
                case 82:
                case 114:
                    try {
                        while (clientSocket.getInputStream().available() != 0) {
                            msg = in.readLine();
                            System.out.println(msg);
                        }
                    } catch (IOException ignored){}
                    break;
            }
        }
    }
    private void send_tcp(){

    }
}
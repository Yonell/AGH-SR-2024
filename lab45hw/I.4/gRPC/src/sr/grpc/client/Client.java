package sr.grpc.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import sr.grpc.gen.ConfigGrpc;
import sr.grpc.gen.Empty;
import sr.grpc.gen.m1;

import java.util.Random;
import java.util.Scanner;

public class Client {
    private final ManagedChannel channel;
    private final ConfigGrpc.ConfigBlockingStub blockstub;
    Scanner in = new Scanner(System.in);
    public Client() {
        super();
        channel = ManagedChannelBuilder.forAddress("127.0.0.5", 50052)
                .usePlaintext() // Channels are secure by default (via SSL/TLS). For the example we disable TLS to avoid needing certificates.
                .build();

        blockstub = ConfigGrpc.newBlockingStub(channel);
    }
    private void start(){
        while (true) {
            System.out.print("Give command: ");
            String command = in.nextLine();
            if (command.equals("exit")) {
                break;
            } else if (command.equals("test1")) {
                System.out.println("Calling test1");
                m1 response = blockstub.test1(Empty.newBuilder().build());
                if (response.hasVal1()) {
                    System.out.println("Received response with val1 = " + response.getVal1());
                } else {
                    System.out.println("Received empty response");
                }
            } else if (command.equals("test2.1")) {
                Random rng = new Random();
                int val1 = rng.nextInt();
                System.out.println("Calling test2 with val1 = " + val1);
                blockstub.test2(m1.newBuilder().setVal1(val1).build());
            } else if (command.equals("test2.2")) {
                Random rng = new Random();
                System.out.println("Calling test2 with empty val1");
                blockstub.test2(m1.newBuilder().build());
            } else {
                System.out.println("Unknown command");
            }
        }

    }
    public static void main(String[] args) {
        Client client = new Client();
        client.start();
    }
}

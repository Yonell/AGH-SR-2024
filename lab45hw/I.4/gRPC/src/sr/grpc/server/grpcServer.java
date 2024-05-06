package sr.grpc.server;

import io.grpc.Server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.concurrent.Executors;

public class grpcServer {

    private Server server;
    private String address = "127.0.0.5";
    private int port = 50052;

    private SocketAddress socket;
    private void start() throws IOException {
        try{
            socket = new InetSocketAddress(InetAddress.getByName(address), port);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        server = io.grpc.ServerBuilder.forPort(port).executor(Executors.newFixedThreadPool(4))
                .addService(new ConfigImpl())
                .build()
                .start();
        System.out.println("Server started, listening on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.err.println("Shutting down gRPC server...");
                grpcServer.this.stop();
                System.err.println("Server shut down.");
            }
        });
    }

    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }
    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }
    public static void main(String[] args) throws InterruptedException, IOException {
        final grpcServer server = new grpcServer();
        server.start();
        server.blockUntilShutdown();
    }
}

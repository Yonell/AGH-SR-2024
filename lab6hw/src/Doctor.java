import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Doctor {
    int id;
    Random rand = new Random();
    Connection connection;
    Channel channel;
    List<String> done = new ArrayList<>();
    Lock lock = new ReentrantLock();
    Scanner in = new Scanner(System.in);

    public Doctor() throws IOException, TimeoutException {
        this.id = rand.nextInt(10000);
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        connection = factory.newConnection();
        channel = connection.createChannel();
        channel.queueDeclare("requestknee", false, false, false, null);
        channel.queueDeclare("requesthip", false, false, false, null);
        channel.queueDeclare("requestelbow", false, false, false, null);
        channel.queueDeclare("response" + id, false, false, false, null);
    }
    public void run() throws IOException {
        this.handleResponse();
        while(true) {
            String command;
            System.out.print("Enter command: ");
            command = in.nextLine();
            switch(command) {
                case "exit":
                    return;
                case "id":
                    System.out.println("ID: " + id);
                    break;
                case "knee":
                    channel.basicPublish("", "request" + "knee", null, ("knee" + " " + Integer.toString(rand.nextInt(10000)) + " " + id).getBytes());
                    break;
                case "hip":
                    channel.basicPublish("", "request" + "hip", null, ("hip" + " " + Integer.toString(rand.nextInt(10000)) + " " + id).getBytes());
                    break;
                case "elbow":
                    channel.basicPublish("", "request" + "elbow", null, ("elbow" + " " + Integer.toString(rand.nextInt(10000)) + " " + id).getBytes());
                    break;
                case "listdone":
                    lock.lock();
                    for(String service: done){
                        System.out.println(service);
                    }
                    done.clear();
                    lock.unlock();
                    break;
                default:
                    System.out.println("Unknown command");
            }
        }
    }
    private void handleResponse() throws IOException {
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                lock.lock();
                done.add(new String(body, "UTF-8"));
                lock.unlock();
            }
        };
        channel.basicConsume("response" + id, true, consumer);
    }
    public static void main(String[] arg) throws IOException, TimeoutException {
        Doctor doc = new Doctor();
        doc.run();
    }
}

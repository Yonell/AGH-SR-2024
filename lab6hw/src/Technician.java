import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeoutException;

public class Technician {
    Random rand = new Random();
    List<String> services;
    Connection connection;
    Channel channel;
    Technician(String[] arg) throws IOException, TimeoutException {
        services = new ArrayList<String>();
        Collections.addAll(services, arg);
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        connection = factory.newConnection();
        channel = connection.createChannel();
        for(String service: services){
            channel.queueDeclare("request" + service, false, false, false, null);
        }
    }
    public void run() throws IOException, TimeoutException {
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("Received: " + message);
                String[] msg = message.split(" ");
                String service = msg[0];
                String client = msg[1];
                String responseDoc = msg[2];
                String responseQueueName = "response" + responseDoc;
                try {
                    Thread.sleep(rand.nextInt(10000));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Done: " + message);
                channel.queueDeclare(responseQueueName, false, false, false, null);
                String response = client + " " + service + " " + "done";
                channel.basicPublish("", responseQueueName, null, response.getBytes());
            }
        };
        for(String service: services){
            channel.basicConsume("request" + service, true, consumer);
        }

    }
    public static void main(String[] arg) throws IOException, TimeoutException {
        Technician tech = new Technician(arg);
        tech.run();
    }
}

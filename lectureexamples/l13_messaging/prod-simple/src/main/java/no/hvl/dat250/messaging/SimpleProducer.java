package no.hvl.dat250.messaging;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class SimpleProducer {

    private static final String QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException, TimeoutException {

        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        if (args.length == 0) {
            System.out.println("No argument provided!");
            System.exit(1);
        }
        try (Connection connection = connectionFactory.newConnection()) {
            Channel channel = connection.createChannel();

            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            for (String arg : args) {
                channel.basicPublish("", QUEUE_NAME, null, arg.getBytes());
            }
            System.out.printf("Sent message %d messages \n", args.length);
        }
    }
}

package no.hvl.dat250.messaging;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class FanoutProducer {

    private static final String EXCHANGE_DECLARE = "hello";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        if (args.length == 0) {
            System.out.println("No argument provided!");
            System.exit(1);
        }
        try (Connection connection = connectionFactory.newConnection()) {
            Channel channel = connection.createChannel();

            channel.exchangeDeclare(EXCHANGE_DECLARE, "fanout");
            for (String arg : args) {
                channel.basicPublish(EXCHANGE_DECLARE, "", null, arg.getBytes());
            }
            System.out.printf("Sent message %d messages \n", args.length);
        }
    }
}

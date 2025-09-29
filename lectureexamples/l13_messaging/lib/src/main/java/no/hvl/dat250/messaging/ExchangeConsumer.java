import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class ExchangeConsumer {

    private final static String EXCHANGE_NAME = "test";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();



        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
        String anonQueue = channel.queueDeclare().getQueue();
        channel.queueBind(anonQueue, EXCHANGE_NAME, "");

        System.out.println("Waiting for messages. To exit press CTRL+C");

        DeliverCallback callback = ((consumerTag, message) -> {
            String result = new String(message.getBody(), StandardCharsets.UTF_8);
            System.out.println(" [x] Received '" + result + "'");
            try {
                doWork(result);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                System.out.println(" [*] Finished '" + result + "'");
            }
            channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
        });
        channel.basicConsume(anonQueue, false,  callback, consumerTag -> {});

    }

    private static void doWork(String task) throws InterruptedException {
        for (char ch: task.toCharArray()) {
            if (ch == '.') Thread.sleep(1000);
        }
    }
}

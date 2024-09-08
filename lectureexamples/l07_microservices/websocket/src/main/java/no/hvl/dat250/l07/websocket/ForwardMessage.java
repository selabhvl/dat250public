package no.hvl.dat250.l07.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

public class ForwardMessage implements Observer {

    Logger logger = LoggerFactory.getLogger(ForwardMessage.class);

    private final WebSocketSession session;

    public ForwardMessage(WebSocketSession session) {
        this.session = session;
    }

    @Override
    public void newMessage(ChatMessage msg) {
        try {
            session.sendMessage(new TextMessage(msg.print()));
        } catch (IOException e) {
            logger.error("could not send message", e);
        }
    }
}

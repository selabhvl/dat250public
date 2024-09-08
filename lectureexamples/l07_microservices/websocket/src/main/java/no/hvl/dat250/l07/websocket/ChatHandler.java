package no.hvl.dat250.l07.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ChatHandler extends TextWebSocketHandler {

    private ChatManger chatManger;

    private Map<WebSocketSession, ForwardMessage> ongoingChats = new HashMap<>();

    private Logger logger = LoggerFactory.getLogger(ChatHandler.class);

    public ChatHandler(@Autowired ChatManger chatManger) {
        this.chatManger = chatManger;
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        chatManger.addMessage(message.getPayload());
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        for (ChatMessage msg : chatManger.getChatHistory()) {
            session.sendMessage(new TextMessage(msg.print()));
        }
        ForwardMessage forward = new ForwardMessage(session);
        chatManger.registerObserver(forward);
        this.ongoingChats.put(session, forward);

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        if (this.ongoingChats.containsKey(session)) {
            this.chatManger.deregisterObser(this.ongoingChats.get(session));
            this.ongoingChats.remove(session);

        }
    }


}

package no.hvl.dat250.l07.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class ChatManger {

    private final List<ChatMessage> chatHistory;
    private final Set<Observer> observers;

    private Logger logger = LoggerFactory.getLogger(ChatManger.class);

    public ChatManger() {
        this.chatHistory = new ArrayList<>();
        this.observers = new HashSet<>();
    }

    public ChatMessage addMessage(String message) {
        ChatMessage result = new ChatMessage(message);
        this.chatHistory.add(result);
        for (Observer o : observers) {
            o.newMessage(result);
        }
        return result;
    }

    public List<ChatMessage> getChatHistory() {
        return chatHistory;
    }

    public void registerObserver(Observer observer) {
        this.observers.add(observer);
        logger.info("Registered new observer: " + observer.hashCode());
    }

    public void deregisterObser(Observer observer) {
        this.observers.remove(observer);
        logger.info("Deregistered observer: " + observer.hashCode());

    }
}

package no.hvl.dat250.l07.websocket;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ChatMessage {

    private String message;

    private LocalDateTime timestamp;

    public ChatMessage(String message) {
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String print() {
        return timestamp.format(DateTimeFormatter.ofPattern("HH:mm:ss")) + ": " + message;
    }
}

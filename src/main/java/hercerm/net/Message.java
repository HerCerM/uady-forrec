package hercerm.net;

import java.io.Serializable;

public final class Message implements Serializable {

    private final String sender;
    private final String message;

    Message(String sender, String message) {
        this.sender = sender;
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public String getMessage() {
        return message;
    }
}

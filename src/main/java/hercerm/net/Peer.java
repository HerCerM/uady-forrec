package hercerm.net;

import java.util.concurrent.Executors;

public abstract class Peer {
    private final Forwarder forwarder;
    private final Receiver receiver;

    public Peer(int port) {
        this.forwarder = new Forwarder();
        this.receiver = new Receiver(port);
        listen(); // Start listening for messages
    }

    public abstract void service(Message message);

    public final void sendMessage(Message message, String host, int port) {
        forwarder.sendMessage(message, host, port);
    }

    private void listen() {
        // Always listen for messages on a separate thread
        Executors.newSingleThreadExecutor().execute(() -> {
            service(receiver.receiveMessage());
            listen();
        });
    }
}

package hercerm.net;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;

public abstract class Peer {
    private final Forwarder forwarder;
    private final Receiver receiver;
    private final String alias;

    public Peer(String alias) {
        Map<String, Entry> registry = buildRegistry();
        this.alias = alias;
        forwarder = new Forwarder(registry);
        receiver = new Receiver(registry.get(alias).getPort());
        listen(); // Start listening for messages
    }

    public abstract void service(Message message);

    public final void sendMessage(String message, String destination) {
        forwarder.sendMessage(new Message(alias, message), destination);
    }

    private void listen() {
        // Always listen for messages on a separate thread
        Executors.newSingleThreadExecutor().execute(() -> {
            service(receiver.receiveMessage());
            listen();
        });
    }

    private Map<String, Entry> buildRegistry() {

        Map<String, Entry> registry = new HashMap<>();
        try {
            File peersFile = new File(Forwarder.class.getResource("/peers.json").toURI());

            Gson gson = new Gson();
            JsonObject[] entries = gson.fromJson(new FileReader(peersFile), JsonObject[].class);

            Arrays.stream(entries).forEach((entry) -> {
                registry.put(entry.get("alias").getAsString(),
                        new Entry(entry.get("host").getAsString(), entry.get("port").getAsInt()));
            });
        } catch (FileNotFoundException | URISyntaxException e) {
            e.printStackTrace();
        }

        return registry;
    }
}

package hercerm.net;

import org.apache.commons.lang3.SerializationUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Map;

final class Forwarder {

    private Map<String, Entry> registry;

    Forwarder(Map<String, Entry> registry) {
        this.registry = registry;
    }

    void sendMessage(Message message, String peerAlias) {
        Entry entry = registry.get(peerAlias);
        deliver(marshal(message), entry.getHost(), entry.getPort());
    }

    private void deliver(byte[] message, String host, int port) {
        try(Socket socket = new Socket(host, port);
            OutputStream writer = socket.getOutputStream();) {

            // Send message to the receiver
            writer.write(message);

        } catch (UnknownHostException e) {
            System.err.println(String.format("Host %s couldn't be located.", host));
        } catch (IOException e) {
            System.err.println("Unidentified IO error.");
        }
    }

    private byte[] marshal(Message message) {
        return SerializationUtils.serialize(message);
    }
}

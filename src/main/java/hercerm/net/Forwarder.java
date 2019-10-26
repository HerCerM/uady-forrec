package hercerm.net;

import org.apache.commons.lang3.SerializationUtils;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public final class Forwarder {

    public void sendMessage(Message message, String host, int port) {
        deliver(marshal(message), host, port);
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

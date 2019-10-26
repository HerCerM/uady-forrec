package hercerm.net;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.SerializationUtils;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public final class Receiver {

    private final int port;

    public Receiver(int port) {
        this.port = port;
    }

    public Message receiveMessage() {
        return unmarshal(receive());
    }

    private byte[] receive() {
        byte[] message = null;

        try(ServerSocket serverSocket = new ServerSocket(port);) {

            // Wait until a message is sent
            Socket socket = serverSocket.accept();
            // Get message as an array of bytes
            message = IOUtils.toByteArray(socket.getInputStream());

        } catch (IOException e) {
            System.err.println("Unidentified IO error.");
        }

        return message;
    }

    private Message unmarshal(byte[] message) {
        return SerializationUtils.deserialize(message);
    }

    public int getPort() {
        return port;
    }
}

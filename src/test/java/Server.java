import hercerm.net.Message;
import hercerm.net.Peer;

public class Server extends Peer {

    public static void main(String[] args) {
        Peer server = new Server("Server");
    }

    public Server(String alias) {
        super(alias);
    }

    @Override
    public void service(Message message) {
        System.out.println(String.format("[Message Received]: %s FROM %s",
                message.getMessage(), message.getSender()));
    }
}

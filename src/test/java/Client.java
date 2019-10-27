import hercerm.net.Message;
import hercerm.net.Peer;

public class Client extends Peer {

    public static void main(String[] args) {
        Peer client = new Client(36);
        client.sendMessage(new Message("PC-HCM", "Hello World"),
                "localhost", 80);
    }

    public Client(int port) {
        super(port);
    }

    @Override
    public void service(Message message) {}
}

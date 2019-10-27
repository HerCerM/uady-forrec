import hercerm.net.Message;
import hercerm.net.Peer;

public class Client extends Peer {

    public static void main(String[] args) {
        Peer client = new Client("Client");
        client.sendMessage( "Hello World", "Server");
    }

    public Client(String peerAlias) {
        super(peerAlias);
    }

    @Override
    public void service(Message message) {}
}

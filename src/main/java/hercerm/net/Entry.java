package hercerm.net;

final class Entry {
    private final String host;
    private final int port;

    Entry(String host, int port) {
        this.host = host;
        this.port = port;
    }

    String getHost() {
        return host;
    }

    int getPort() {
        return port;
    }
}

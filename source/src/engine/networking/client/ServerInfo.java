package engine.networking.client;

import java.net.InetAddress;

public class ServerInfo {
    
    public InetAddress address;
    public int port;

    public ServerInfo(InetAddress address, int port) {
        this.address = address;
        this.port = port;
    }
}

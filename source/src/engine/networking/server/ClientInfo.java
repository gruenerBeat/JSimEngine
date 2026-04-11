package engine.networking.server;

import java.net.InetAddress;

public class ClientInfo {
    
    public String name;
    public InetAddress address;
    public int port;

    public ClientInfo(String name, InetAddress address, int port) {
        this.name = name;
        this.address = address;
        this.port = port;
    }
}

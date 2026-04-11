package engine.networking.client;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

import engine.networking.Packer;
import engine.objects.Camera;
import engine.types.World;

public class Client {
    
    private ServerInfo server;
    private String clientName;
    private String gameId;
    private DatagramSocket socket;

    public Client(ServerInfo server, String gameId, String clientName) {
        this.server = server;
        this.gameId = gameId;
        this.clientName = clientName;
    }

    public boolean initializeConnection() {
        try {
            socket = new DatagramSocket();

            String payload = "JSE_" + gameId + "#" + clientName + "" + "@" + server.address.getHostAddress() + ":CONREQ";

            byte[] buffer = payload.getBytes();

            DatagramPacket request = new DatagramPacket(buffer, buffer.length, server.address, server.port);
            socket.send(request);

            buffer = new byte[1024];
            DatagramPacket response = new DatagramPacket(buffer, buffer.length);
            socket.receive(response);

            payload = new String(buffer).trim();
            String[] parts = payload.split("#");
            if(parts[0] != "JSE_" + gameId) {
                System.out.println("Unknown Host");
                socket.close();
                return false;
            }

            payload = parts[1].split(":")[1];
            parts = payload.split(",");

            if(parts[0] != "CONACC") {
                System.out.println("Server refused connection");
                socket.close();
                return false;
            }

            int worldSize = Integer.parseInt(parts[1]);

            //TODO; Implement encryption

            buffer = new byte[worldSize];
            response = new DatagramPacket(buffer, buffer.length);
            socket.receive(response);

            payload = new String(buffer).trim();
            parts = payload.split("#");
            if(parts[0] != "JSE_" + gameId) {
                System.out.println("Faulty Server response");
                socket.close();
                return false;
            }
            
            payload = parts[1].split(":")[1];
            parts = payload.split(",");

            if(parts[0] != "WORDEL") {
                System.out.println("Faulty Server response");
                socket.close();
                return false;
            }

            String worldString = parts[1];
            String camName = parts[2];

            Packer p = new Packer();
            World.changeWorld(p.unpackageWorld(worldString.getBytes()));
            Camera.changeCamera(World.getCurrent().findObject(camName));

            return true;
        } catch(Exception e) {

            e.printStackTrace();
            return false;
        }
    }

    public void close() {
        socket.close();
    }
}

package engine.networking.server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import engine.logic.Main;

public class ConnectionListener extends Thread {

    private DatagramSocket socket;
    private String gameId;

    public ConnectionListener(int port, String gameId) {
        this.gameId = gameId;
        try {

            socket = new DatagramSocket(port);
        } catch(Exception e) {
            e.printStackTrace();
            return;
        }
    }

    @Override
    public void run() {
        
        while (Main.isRunning()) {
            
            try {
                byte[] buffer = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                String msg = new String(buffer).trim();
                String[] parts = msg.split("#");
                if(parts[0] != "JSE_" + gameId) {
                    continue;
                }

                String clientInfo = parts[1].split("@")[0];
                String serverCall = parts[1].split("@")[1].split(":")[0];

                if(InetAddress.getByName(serverCall) != socket.getLocalAddress()) {
                    continue;
                }

                

            } catch(Exception e) {
                e.printStackTrace();
                continue;
            }
        }
    }
    
}

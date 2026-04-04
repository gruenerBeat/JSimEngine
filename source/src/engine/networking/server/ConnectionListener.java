package engine.networking.server;

import java.net.DatagramSocket;

import engine.logic.Main;

public class ConnectionListener extends Thread {

    private DatagramSocket socket;

    public ConnectionListener(int port) {
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
            
            //TODO: Write Server reciever
            //And build client system
        }
    }
    
}

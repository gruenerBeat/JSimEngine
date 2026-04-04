package engine.types;

import java.net.InetAddress;

import engine.networking.NetworkMode;
import engine.rendering.RenderType;

public class GameInitializer {

    public String name = "Unnamed Game";
    public String gameId = "author.game";

    public NetworkMode networkMode = NetworkMode.CLIENT_ATTACHED_SERVER;

    public InetAddress serverAddress;
    public int serverPort;
    public String clientName;
    public RenderType rt = RenderType.SOFTWARE;
    public int screenWidth = 100;
    public int screenHeight = 100;
    public int targetFPS = 20;
    public double fov = 90;

    public int targetTPS = 20;
    public World world = new World("Default");
}

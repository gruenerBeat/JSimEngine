package engine.types;

import engine.rendering.RenderType;

public class GameInitializer {

    //Meta Information
    public String name = "Unnamed Game";

    //Rendering and Camera
    public RenderType rt = RenderType.SOFTWARE;
    public int screenWidth = 100;
    public int screenHeight = 100;
    public int targetFPS = 20;
    public double fov = 90;

    //Game Settings
    public int targetTPS = 20;
    public World world = new World("Default");
}

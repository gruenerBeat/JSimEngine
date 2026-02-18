package engine.types;

import engine.rendering.RenderType;

public class GameInitializer {

    //Meta Information
    public String name;

    //Rendering and Camera
    public RenderType rt;
    public int screenWidth;
    public int screenHeight;
    public int targetFPS;
    public double fov;

    //Game Settings
    public int targetTPS;
    public World world = new World("Default");
}

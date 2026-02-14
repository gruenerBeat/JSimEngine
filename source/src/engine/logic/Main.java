package engine.logic;

import engine.display.GameWindow;
import engine.objects.Camera;
import engine.rendering.RayTracing;
import engine.rendering.RenderType;
import engine.rendering.Renderer;
import engine.rendering.Software;
import engine.types.GameInitializer;
import engine.types.World;
import game.Game;

public class Main {

    private static String name;
    private static RenderType rendering;
    private static GameWindow window;
    private static boolean running;
    private static int targetFPS;
    private static double actualFPS;
    private static long frame;
    private static long gameTime;
    private static double deltaTime;
    private static long runningTime = 0;
    private static int targetTPS;
    private static double actualTPS;
    private static long gameRunningTime = 0;
    private static double mspt;
    private static Renderer renderer;

    public void main(String[] args) {

        Game game = new Game();
        GameInitializer gi = game.register();
        
        name = gi.name;
        rendering = gi.rt;
        targetFPS = gi.targetFPS;
        targetTPS = gi.targetTPS;

        World.changeWorld(new World("Default"));
        Camera.changeCamera(new Camera("Main Camera"));

        running = true;
        window = new GameWindow(name, gi.screenWidth, gi.screenHeight);

        switch (rendering) {
            case SOFTWARE:
                renderer = new Software(gi.screenWidth, gi.screenHeight);
                break;
            case RAY_TRACING:
                renderer = new RayTracing(gi.screenWidth, gi.screenHeight);
                break;
        }

        RenderClock rendererClock = new RenderClock(renderer);
        GameClock gameClock = new GameClock();

        rendererClock.start();
        gameClock.start();
    }

    public static double getActualFPS() {
        return actualFPS;
    }
    
    public static double getActualTPS() {
        return actualTPS;
    }

    public static double getDeltaTime() {
        return deltaTime;
    }

    public static long getFrame() {
        return frame;
    }

    public static long getGameTime() {
        return gameTime;
    }

    public static double getMspt() {
        return mspt;
    }

    public static String getName() {
        return name;
    }

    public static RenderType getRendering() {
        return rendering;
    }

    public static int getTargetFPS() {
        return targetFPS;
    }

    public static int getTargetTPS() {
        return targetTPS;
    }

    public static GameWindow getWindow() {
        return window;
    }

    public static boolean isRunning() {
        return running;
    }

    public static void frameTicked() {
        frame++;
        long time = System.nanoTime() / 1000000;
        deltaTime = time - runningTime;
        runningTime = time;
        actualFPS = (double)1000 / deltaTime;
    }

    public static void gameTicked() {
        gameTime++;
        long time = System.nanoTime() / 1000000;
        mspt = time - gameRunningTime;
        gameRunningTime = time;
        actualTPS = (double)1000 / mspt;
    }
}

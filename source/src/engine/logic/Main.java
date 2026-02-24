package engine.logic;

import java.lang.reflect.Method;

import engine.display.GameWindow;
import engine.objects.Camera;
import engine.rendering.RenderType;
import engine.rendering.Renderer;
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

        System.out.println("Loaded World: " + gi.world.name);
        Camera mainCam = new Camera("Main Camera", gi.fov, gi.screenHeight, (double)gi.screenWidth / (double)gi.screenHeight);
        World.changeWorld(gi.world);
        World.getCurrent().addObject(mainCam);
        Camera.changeCamera(mainCam);

        window = GameWindow.getInstance(name, gi.screenWidth, gi.screenHeight);

        System.out.println("Use Render-pipeline: " + rendering);
        try {
            Class<?>[] renderParams = {int.class, int.class};
            Method renderClass = rendering.getRenderClass().getMethod("getInstance", renderParams);
            renderer = (Renderer)renderClass.invoke(null, gi.screenWidth, gi.screenHeight);
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("Couldn't create Renderer!");
            return;
        }

        RenderClock rendererClock = new RenderClock(renderer);
        GameClock gameClock = new GameClock();

        game.init();

        running = true;

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

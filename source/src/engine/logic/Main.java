package engine.logic;

import java.lang.reflect.Method;

import engine.display.GameWindow;
import engine.logic.clientside.RenderClock;
import engine.logic.serverside.GameClock;
import engine.networking.NetworkMode;
import engine.networking.client.Client;
import engine.networking.client.ServerInfo;
import engine.rendering.RenderType;
import engine.rendering.Renderer;
import engine.types.GameInitializer;
import engine.types.World;
import game.Game;

public class Main {

    private static String name;
    private static String gameId;
    private static Client client;
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


    private Renderer initRenderer(RenderType rendering, int screenWidth, int screenHeight) {
        try {
            Class<?>[] renderParams = {int.class, int.class};
            Method renderClass = rendering.getRenderClass().getMethod("getInstance", renderParams);
            return (Renderer)renderClass.invoke(null, screenWidth, screenHeight);
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void main(String[] args) {
        
        Game game = new Game();
        GameInitializer gi = game.register();
        name = gi.name;

        RenderClock rendererClock = new RenderClock(renderer);
        GameClock gameClock = new GameClock();

        if(gi.networkMode == NetworkMode.CLIENT) {

            rendering = gi.rt;
            targetFPS = gi.targetFPS;

            renderer = initRenderer(rendering, gi.screenWidth, gi.screenHeight);
            window = GameWindow.getInstance(name, gi.screenWidth, gi.screenHeight);

            client = new Client(new ServerInfo(gi.serverAddress, gi.serverPort), gameId, gi.clientName);

            if(!client.initializeConnection()) {
                return;
            }

            running = true;
            rendererClock.start();

        } else if(gi.networkMode == NetworkMode.SERVER) {

            targetTPS = gi.targetTPS;
            World.changeWorld(gi.world);

            game.init();
            running = true;
            gameClock.start();

        } else if(gi.networkMode == NetworkMode.CLIENT_ATTACHED_SERVER) {

            rendering = gi.rt;
            targetFPS = gi.targetFPS;
            targetTPS = gi.targetTPS;

            renderer = initRenderer(rendering, gi.screenWidth, gi.screenHeight);
            window = GameWindow.getInstance(name, gi.screenWidth, gi.screenHeight);

            game.init();
            running = true;
            gameClock.start();
            rendererClock.start();
        }

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

    public static Client getClient() {
        return client;
    }

    public static String getGameId() {
        return gameId;
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

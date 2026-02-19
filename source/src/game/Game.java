package game;

import engine.logic.GameRegister;
import engine.rendering.RenderType;
import engine.types.GameInitializer;

public class Game extends GameRegister {
    
    @Override
    public GameInitializer register() {
        GameInitializer init = new GameInitializer();
        init.name = "Test-Game";
        init.rt = RenderType.TEST_RENDERER;
        init.screenWidth = 400;
        init.screenHeight = 400;
        init.targetFPS = 20;
        init.fov = 90;
        init.targetTPS = 1;

        return init;
    }
}

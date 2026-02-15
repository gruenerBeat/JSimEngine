package game;

import engine.logic.GameRegister;
import engine.rendering.RenderType;
import engine.types.GameInitializer;

public class Game extends GameRegister {
    
    @Override
    public GameInitializer register() {
        GameInitializer init = new GameInitializer();
        init.name = "Test-Game";
        init.rt = RenderType.SOFTWARE;
        init.screenWidth = 1280;
        init.screenHeight = 720;
        init.targetFPS = 20;
        init.targetTPS = 20;
        return init;
    }
}

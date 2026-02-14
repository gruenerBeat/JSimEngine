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
        init.screenWidth = 800;
        init.screenHeight = 800;
        init.targetFPS = 20;
        init.targetTPS = 20;
        return init;
    }
}

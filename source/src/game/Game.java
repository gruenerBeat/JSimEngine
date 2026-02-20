package game;

import engine.logic.GameRegister;
import engine.types.GameInitializer;

public class Game extends GameRegister {
    
    @Override
    public GameInitializer register() {
        return new GameInitializer();
    }

    @Override
    public void init() {}
}

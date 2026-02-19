package game;

import engine.logic.GameRegister;
import engine.objects.Empty;
import engine.rendering.RenderType;
import engine.types.GameInitializer;
import engine.types.Object;
import engine.types.World;

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

	World world = new World("Test");
	Object obj = new Empty("Test Object");
	obj.addProperty(new MatrixTest());
	world.addObject(obj);
	init.world = world;

        return init;
    }
}

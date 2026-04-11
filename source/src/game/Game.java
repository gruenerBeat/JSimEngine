package game;

import engine.libs.math.Vector;
import engine.logic.GameRegister;
import engine.objects.Camera;
import engine.properties.CameraProperty;
import engine.properties.PropertyType;
import engine.types.GameInitializer;

public class Game extends GameRegister {
    
    @Override
    public GameInitializer register() {
        GameInitializer gi = new GameInitializer();
        gi.targetFPS = 20;
        gi.targetTPS = 20;
        gi.screenWidth = 1280;
        gi.screenHeight = 720;
        return gi;
    }

    @Override
    public void init() {

        Camera.getCurrent().transform().setPosition(new Vector(new double[]{3, 0, 0}));
        ((CameraProperty)Camera.getCurrent().findProperty(PropertyType.CAMERA)).lookAt(new Vector(new double[]{0, 0, 0}));

        Camera.getCurrent().addProperty(new RotatingCamera(0.1, 3, 0));
    }
}

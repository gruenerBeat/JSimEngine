package game;
 
import engine.libs.math.Vector;
import engine.libs.types.Color.Color;
import engine.logic.GameRegister;
import engine.objects.Camera;
import engine.objects.Empty;
import engine.properties.CameraProperty;
import engine.properties.PropertyType;
import engine.properties.renderers.SphereRenderer;
import engine.rendering.RenderType;
import engine.types.GameInitializer;
import engine.types.Object;
import engine.types.World;

public class Game extends GameRegister {
    
    @Override
    public GameInitializer register() {
        GameInitializer init = new GameInitializer();
        init.name = "Test";
        init.rt = RenderType.RAY_TRACING;
        init.fov = 60;
        init.screenHeight = 720;
        init.screenWidth = 1280;
        init.targetFPS = 20;
        init.targetTPS = 20;
        return init;
    }

    @Override
    public void init() {

        Camera.getCurrent().transform().setPosition(new Vector(new double[]{3, 0, 0}));
        ((CameraProperty)Camera.getCurrent().findProperty(PropertyType.CAMERA)).lookAt(new Vector(new double[]{0, 0, 0}));

        Object sphere1 = new Empty("sphere1");
        sphere1.transform().setPosition(new Vector(new double[]{0, 0, 0}));
        sphere1.addProperty(new SphereRenderer(1, new Color(255, 0, 0)));
        World.getCurrent().addObject(sphere1);

        Object sphere2 = new Empty("sphere2");
        sphere2.transform().setPosition(new Vector(new double[]{0, 2, -1}));
        sphere2.addProperty(new SphereRenderer(0.5, new Color(0, 255, 0)));
        World.getCurrent().addObject(sphere2);

        Object sphere3 = new Empty("sphere3");
        sphere3.transform().setPosition(new Vector(new double[]{0, -3, 2}));
        sphere3.addProperty(new SphereRenderer(1.2, new Color(255, 255, 0)));
        World.getCurrent().addObject(sphere3);
    }
}

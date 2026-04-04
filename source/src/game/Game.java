package game;

import engine.libs.types.Material;
import engine.libs.types.Color.Color;
import engine.logic.GameRegister;
import engine.objects.Camera;
import engine.objects.Empty;
import engine.properties.CameraProperty;
import engine.properties.PropertyType;
import engine.properties.renderers.SphereRenderer;
import engine.types.GameInitializer;
import engine.types.World;
import engine.types.Object;
import engine.libs.math.Vector;;

public class Game extends GameRegister {
    
    @Override
    public GameInitializer register() {
        GameInitializer gi = new GameInitializer();
        gi.targetFPS = 1;
        gi.targetTPS = 1;
        return gi;
    }

    @Override
    public void init() {

        Camera.getCurrent().transform().setPosition(new Vector(new double[]{10, 0, 0}));
        ((CameraProperty)Camera.getCurrent().findProperty(PropertyType.CAMERA)).lookAt(new Vector(new double[]{0, 0, 0}));

        Object s1 = new Empty("Sphere1");
        s1.addProperty(new SphereRenderer(1, new Material(new Color(255, 127, 0))));
        s1.transform().setPosition(new Vector(new double[]{2, -3, 2}));
        World.getCurrent().addObject(s1);

        Object s2 = new Empty("Sphere2");
        s2.addProperty(new SphereRenderer(1, new Material(new Color(255, 0, 255))));
        s2.transform().setPosition(new Vector(new double[]{1, 4, -4}));
        World.getCurrent().addObject(s2);

        Object s3 = new Empty("Sphere3");
        s3.addProperty(new SphereRenderer(3, new Material(new Color(255, 0, 0))));
        s3.transform().setPosition(new Vector(new double[]{2, -2, -5}));
        World.getCurrent().addObject(s3);

        Object s4 = new Empty("Sphere4");
        s4.addProperty(new SphereRenderer(5, new Material(new Color(0, 255, 255))));
        s4.transform().setPosition(new Vector(new double[]{-4, -5, 5}));
        World.getCurrent().addObject(s4);

        Object s5 = new Empty("Sphere5");
        s5.addProperty(new SphereRenderer(0.5, new Material(new Color(255, 255, 255))));
        s5.transform().setPosition(new Vector(new double[]{3, 2, 1}));
        World.getCurrent().addObject(s5);

        Object s6 = new Empty("Sphere6");
        s6.addProperty(new SphereRenderer(0.25, new Material(new Color(255, 255, 0))));
        s6.transform().setPosition(new Vector(new double[]{0, -1, -1}));
        World.getCurrent().addObject(s6);

        Object s7 = new Empty("Sphere7");
        s7.addProperty(new SphereRenderer(0.25, new Material(new Color(0, 255, 0))));
        s7.transform().setPosition(new Vector(new double[]{5, 2, -1}));
        World.getCurrent().addObject(s7);

        Object s8 = new Empty("Sphere8");
        s8.addProperty(new SphereRenderer(7, new Material(new Color(0, 0, 255))));
        s8.transform().setPosition(new Vector(new double[]{-10, 0, 9}));
        World.getCurrent().addObject(s8);

        System.out.println(World.getCurrent().pack());
    }
}

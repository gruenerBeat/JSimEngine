package game;

import engine.libs.math.Vector;
import engine.libs.types.Mesh;
import engine.libs.types.Color.Color;
import engine.logic.GameRegister;
import engine.objects.Camera;
import engine.objects.Empty;
import engine.properties.renderers.MeshRenderer;
import engine.rendering.Utils.Triangle;
import engine.types.GameInitializer;
import engine.types.Object;
import engine.types.World;

public class Game extends GameRegister {
    
    @Override
    public GameInitializer register() {
        GameInitializer gi = new GameInitializer();
        gi.targetFPS = 1;
        gi.targetTPS = 20;
        gi.screenWidth = 1280;
        gi.screenHeight = 720;
        gi.fov = 90;
        return gi;
    }

    @Override
    public void init() {

        Camera.getCurrent().transform().setPosition(new Vector(new double[]{1, 0, 1}));
        Mesh planeMesh = new Mesh(new Triangle[] {
            new Triangle(
                new Vector(new double[]{0.5, 0.5, 0}),
                new Vector(new double[]{0.5, -0.5, 0}),
                new Vector(new double[]{-0.5, -0.5, 0}),
                new Color(255, 0, 0)
            ),
            new Triangle(
                new Vector(new double[]{-0.5, -0.5, 0}),
                new Vector(new double[]{-0.5, 0.5, 0}),
                new Vector(new double[]{0.5, 0.5, 0}),
                new Color(0, 255, 0)
            )
        });

        Mesh cubeMesh = new Mesh(new Triangle[]{
            new Triangle(
                new Vector(new double[]{-1, -1, 1}),
                new Vector(new double[]{1, -1, 1}),
                new Vector(new double[]{1, 1, 1}),
                new Color(255, 0, 0)
            ),
            new Triangle(
                new Vector(new double[]{1, 1, 1}),
                new Vector(new double[]{-1, 1, 1}),
                new Vector(new double[]{-1, -1, 1}),
                new Color(0, 255, 0)
            ),
            new Triangle(
                new Vector(new double[]{1, 1, 1}),
                new Vector(new double[]{1, 1, -1}),
                new Vector(new double[]{1, -1, -1}),
                new Color(0, 0, 255)
            ),
            new Triangle(
                new Vector(new double[]{1, -1, -1}),
                new Vector(new double[]{1, -1, 1}),
                new Vector(new double[]{1, 1, 1}),
                new Color(255, 255, 0)
            ),
            new Triangle(
                new Vector(new double[]{1, 1, 1}),
                new Vector(new double[]{1, 1, -1}),
                new Vector(new double[]{-1, 1, -1}),
                new Color(0, 255, 255)
            ),
            new Triangle(
                new Vector(new double[]{-1, 1, -1}),
                new Vector(new double[]{-1, 1, 1}),
                new Vector(new double[]{1, 1, 1}),
                new Color(255, 0, 255)
            ),
            new Triangle(
                new Vector(new double[]{-1, 1, 1}),
                new Vector(new double[]{-1, 1, -1}),
                new Vector(new double[]{-1, -1, -1}),
                new Color(255, 255, 255)
            ),
            new Triangle(
                new Vector(new double[]{-1, -1, -1}),
                new Vector(new double[]{-1, -1, 1}),
                new Vector(new double[]{-1, 1, 1}),
                new Color(127, 255, 0)
            ),
            new Triangle(
                new Vector(new double[]{1, -1, 1}),
                new Vector(new double[]{1, -1, -1}),
                new Vector(new double[]{1, -1, -1}),
                new Color(0, 255, 127)
            ),
            new Triangle(
                new Vector(new double[]{1, -1, -1}),
                new Vector(new double[]{-1, -1, 1}),
                new Vector(new double[]{1, -1, 1}),
                new Color(255, 127, 0)
            ),
            new Triangle(
                new Vector(new double[]{-1, -1, -1}),
                new Vector(new double[]{1, -1, -1}),
                new Vector(new double[]{1, 1, -1}),
                new Color(255, 0, 127)
            ),
            new Triangle(
                new Vector(new double[]{1, 1, -1}),
                new Vector(new double[]{-1, 1, -1}),
                new Vector(new double[]{-1, -1, -1}),
                new Color(0, 127, 255)
            )
        });

        Object cube = new Empty("Cube");
        cube.addProperty(new MeshRenderer(planeMesh));
        //cube.addProperty(new Rotate(0.1));
        cube.transform().setPosition(new Vector(new double[]{0, 0, -1}));
        World.getCurrent().addObject(cube);
    }
}

package engine.rendering;

import engine.libs.types.Texture;
import engine.properties.PropertyType;
import engine.rendering.Utils.Ray;
import engine.types.Object;
import engine.types.World;

public class RayTracing extends Renderer {

    public int maxBounces = 10;

    private static RayTracing rayTracing;

    private Ray[][] rays;

    private RayTracing(int width, int height) {
        super(width, height);
        rays = new Ray[width][height];
    }

    public static RayTracing getInstance(int width, int height) {
        if(rayTracing == null) {
            rayTracing = new RayTracing(width, height);
        }
        return rayTracing;
    }

    public static RayTracing getInstance() {
        return rayTracing;
    }

    @Override
    public Texture render(Object cam, World world) {
        assert cam.hasProperty(PropertyType.CAMERA) : "Object isn't a camera";

        //TODO: Create Ray tracer
        return null;
    }

    @Override
    public void setup(Object cam, World world) {
        assert cam.hasProperty(PropertyType.CAMERA) : "Object isn't a camera";
    }


    @Override
    public void update(Object cam, World world) {
    }
}

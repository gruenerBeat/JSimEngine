package engine.rendering;

import engine.libs.types.Texture;
import engine.properties.PropertyType;
import engine.types.Object;
import engine.types.World;

public class RayTracing extends Renderer {

    private static RayTracing rayTracing;

    private RayTracing(int width, int height) {
        super(width, height);
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
        if(!cam.hasProperty(PropertyType.CAMERA)) return new Texture(width, height);

        //TODO : Code RayTracer

        return new Texture(width, height);
    }
}

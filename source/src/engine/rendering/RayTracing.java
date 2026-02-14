package engine.rendering;

import engine.libs.types.Texture;
import engine.libs.types.Color.Color;
import engine.properties.PropertyType;
import engine.types.Object;
import engine.types.World;

public class RayTracing<T extends Color> extends Renderer<T> {

    public RayTracing(int width, int height) {
        super(width, height);
    }

    @Override
    public Texture<T> render(Object cam, World world) {
        if(!cam.hasProperty(PropertyType.CAMERA)) return new Texture<T>(width, height);

        //TO-DO : Code Software Rasterizer

        return new Texture<T>(width, height);
    }
}

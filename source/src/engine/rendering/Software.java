package engine.rendering;

import engine.libs.types.Texture;
import engine.properties.PropertyType;
import engine.types.Object;
import engine.types.World;

public class Software extends Renderer {
    
    public Software(int width, int height) {
        super(width, height);
    }

    @Override
    public Texture render(Object cam, World world) {
        if(!cam.hasProperty(PropertyType.CAMERA)) return new Texture(width, height);

        //TO-DO : Code Software Rasterizer

        return new Texture(width, height);
    }
}

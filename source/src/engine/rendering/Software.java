package engine.rendering;

import engine.libs.types.Texture;
import engine.properties.PropertyType;
import engine.types.Object;
import engine.types.World;

public class Software extends Renderer {
    
    private static Software software;

    private Software(int width, int height) {
        super(width, height);
    }

    public static Software getInstance(int width, int height) {
        if(software == null) {
            software = new Software(width, height);
        }
        return software;
    }

    public static Software getInstance() {
        return software;
    }
    
    @Override
    public Texture render(Object cam, World world) {
        if(!cam.hasProperty(PropertyType.CAMERA)) return new Texture(width, height);

        for(Object obj : world.getObjects()) {
            if(obj.hasProperty(PropertyType.MESH_RENDERER)) {

            }
        }

        Texture tex = new Texture(width, height);

        
        return tex;
    }
}

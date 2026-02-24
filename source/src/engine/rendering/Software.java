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
    public void setup(Object cam, World world) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Texture render(Object cam, World world) {
        assert cam.hasProperty(PropertyType.CAMERA) : "Object isn't a camera";

        //TODO : Code Software Rasterizer

        return new Texture(width, height);
    }

    @Override
    public void update(Object cam, World world) {
        // TODO Auto-generated method stub
        
    }
}

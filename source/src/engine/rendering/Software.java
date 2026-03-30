package engine.rendering;

import java.util.ArrayList;

import engine.libs.math.Vector;
import engine.libs.types.Texture;
import engine.properties.PropertyType;
import engine.properties.renderers.SphereRenderer;
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
        assert cam.hasProperty(PropertyType.CAMERA) : "Object isn't a camera.";

        ArrayList<Object> objs = world.getObjects();
        ArrayList<Object> spheres = new ArrayList<>();
        for(Object object : objs) {
            if(object.hasProperty(PropertyType.SPHERE_RENDERER)) {
                spheres.add(object);
            }
        }

        Vector[] spherePos = new Vector[spheres.size()];
        double[] sphereRadiae = new double[spheres.size()];
        for(int i = 0; i < spheres.size(); i++) {
            spherePos[i] = spheres.get(i).transform().getPosition();
            sphereRadiae[i] = ((SphereRenderer)spheres.get(i).findProperty(PropertyType.SPHERE_RENDERER)).radius;
        }
        

        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
               

            }
        }

        return new Texture(width, height);
    }

    @Override
    public void update(Object cam, World world) {
        // TODO Auto-generated method stub
        
    }
}

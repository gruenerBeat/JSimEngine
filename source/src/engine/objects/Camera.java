package engine.objects;

import engine.types.Object;
import engine.types.Property;
import engine.properties.CameraProperty;
import engine.properties.PropertyType;
import engine.properties.Transform;

public class Camera extends Object{
    
    private static Object current;

    public static Camera getCurrent() {
        return ((Camera)current);
    }

    public static void changeCamera(Object cam) {
        assert cam.hasProperty(PropertyType.CAMERA) : "Object isn't a camera";
        current = cam;
    }

    public Camera(String name, double fov, double sensorDimension, int width, int height) {
        super(name);
        addProperty(new Transform());
        addProperty(new CameraProperty(fov, width, height, sensorDimension));
    }

    public CameraProperty camera() {
        return ((CameraProperty)findProperty(PropertyType.CAMERA));
    }

    @Override
    @Deprecated
    public Property findProperty(PropertyType type) {
        // TODO Auto-generated method stub
        return super.findProperty(type);
    }
}

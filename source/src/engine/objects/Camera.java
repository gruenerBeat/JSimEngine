package engine.objects;

import engine.types.Object;
import engine.properties.CameraProperty;
import engine.properties.PropertyType;

public class Camera extends Object{
    
    private static Object current;

    public static Object getCurrent() {
        return current;
    }

    public static void changeCamera(Object cam) {
        assert cam.hasProperty(PropertyType.CAMERA) : "Object isn't a camera";
        current = cam;
    }

    public Camera(String name, double fov, int sensorDimension, double aspectRatio) {
        super(name);
        addProperty(new CameraProperty(fov, sensorDimension, aspectRatio));
    }
}

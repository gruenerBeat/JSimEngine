package engine.objects;

import engine.types.Object;
import engine.properties.CameraProperty;
import engine.properties.PropertyType;

public class Camera extends Object{
    
    private static Object current;

    public static Object getCurrent() {
        return current;
    }

    public static boolean changeCamera(Object cam) {
        if(cam.hasProperty(PropertyType.CAMERA)) {
            current = cam;
            return true;
        } else {
            return false;
        }
    }

    public Camera(String name) {
        super(name);
        addProperty(new CameraProperty());
    }
}

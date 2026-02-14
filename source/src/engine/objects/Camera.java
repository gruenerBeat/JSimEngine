package engine.objects;

import engine.types.Object;
import engine.properties.CameraProperty;

public class Camera extends Object{
    
    public Camera(String name) {
        super(name);
        addProperty(new CameraProperty());
    }
}

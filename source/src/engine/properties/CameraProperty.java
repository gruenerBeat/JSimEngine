package engine.properties;

import engine.types.Property;

public class CameraProperty extends Property{
    
    public CameraProperty() {
        super("Camera", PropertyType.CAMERA);
    }

    @Override
    public void instantiate() {}
}

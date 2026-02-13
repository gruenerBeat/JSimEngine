package engine.properties;

import engine.libs.math.Vector;
import engine.types.Property;

public class Transform extends Property{
    
    public Vector position;
    public Vector rotation;
    public Vector scale;

    public Transform() {
        super("Transform", PropertyType.TRANSFORM);
        position = new Vector(3);
        rotation = new Vector(3);
        scale = new Vector(3);
        reset();
    }

    public void reset() {
        for(int i = 0; i <= 2; i++) {
            position.val[i] = 0;
            rotation.val[i] = 0;
            scale.val[i] = 1;
        } 
    }

    public void translate(Vector a) {
        if(a.getSize() != 3) return;
        position = Vector.add(position, a);
    }

    public void rotate(Vector a) {
        if(a.getSize() != 3) return;
        rotation = Vector.add(rotation, a);
    }

    public void scale(Vector a) {
        if(a.getSize() != 3) return;
        scale = Vector.hadamard(scale, a);
    }

    @Override
    public void instantiate() {}
}

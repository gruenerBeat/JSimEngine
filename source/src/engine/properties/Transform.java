package engine.properties;

import engine.libs.math.Matrix;
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
    public void initialize() {}

    @Override
    public void tick() {}

    public Matrix localToWorldMatrix() {
        double[][] valTranslation = {
            {1, 0, 0, position.val[0]},
            {0, 1, 0, position.val[1]},
            {0, 0, 1, position.val[2]},
            {0, 0, 0, 1}
        };
        double[][] valRotX = {
            {1, 0, 0, 0},
            {0, Math.cos(rotation.val[0]), Math.sin(rotation.val[0]), 0},
            {0, -Math.sin(rotation.val[0]), Math.cos(rotation.val[0]), 0},
            {0, 0, 0, 1}
        };
        double[][] valRotY = {
            {Math.cos(rotation.val[1]), 0, -Math.sin(rotation.val[1]), 0},
            {0, 1, 0, 0},
            {Math.sin(rotation.val[1]), 0, Math.cos(rotation.val[1]), 0},
            {0, 0, 0, 1}
        };
        double[][] valRotZ = {
            {Math.cos(rotation.val[2]), -Math.sin(rotation.val[2]), 0, 0},
            {Math.sin(rotation.val[2]), Math.cos(rotation.val[2]), 0, 0},
            {0, 0, 1, 0},
            {0, 0, 0, 1}
        };
        Matrix translationMatrix = new Matrix(4, 4);
        translationMatrix.val = valTranslation;
        Matrix rotationXMatrix = new Matrix(4, 4);
        rotationXMatrix.val = valRotX;
        Matrix rotationYMatrix = new Matrix(4, 4);
        rotationYMatrix.val = valRotY;
        Matrix rotationZMatrix = new Matrix(4, 4);
        rotationZMatrix.val = valRotZ;
        Matrix rotationMatrix = rotationZMatrix.act(rotationYMatrix.act(rotationXMatrix));
        return rotationMatrix.act(translationMatrix);
    }

    public Matrix worldToLocalMatrix() {
        return localToWorldMatrix().inverse();
    }
}

package engine.properties;

import engine.libs.math.Matrix;
import engine.libs.math.Vector;
import engine.types.Property;

public class Transform extends Property{
    
    private Vector position;
    private Vector xVector;
    private Vector yVector;
    private Vector zVector;

    public Transform() {
        super("Transform", PropertyType.TRANSFORM);
        reset();
    }

    public void reset() {
        position = new Vector(new double[]{0, 0, 0});
        xVector = new Vector(new double[]{1, 0, 0});
        yVector = new Vector(new double[]{0, 1, 0});
        zVector = new Vector(new double[]{0, 0, 1});
    }

    public Vector getPosition() {
        return position;
    }

    public void translate(Vector v) {
        position = Vector.add(position, v);
    }

    @Override
    public void initialize() {}

    @Override
    public void tick() {}

    public Matrix getWorldToLocalMatrix() {
        return new Matrix(new double[][]{
            {xVector.val[0], xVector.val[1], xVector.val[2], position.val[0]},
            {yVector.val[0], yVector.val[1], yVector.val[2], position.val[1]},
            {zVector.val[0], zVector.val[1], zVector.val[2], position.val[2]},
            {0, 0, 0, 1}
        }, 4, 4);
    }
}

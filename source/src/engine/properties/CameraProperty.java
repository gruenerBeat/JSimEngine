package engine.properties;

import engine.libs.math.Matrix;
import engine.libs.math.Trig;
import engine.types.Property;

public class CameraProperty extends Property{

    private double fov;
    private double focalLength;
    private int sensorDimension;
    private double aspectRatio;

    public CameraProperty(double fov, int sensorDimension, double aspectRatio) {
        this.fov = fov;
        this.sensorDimension = sensorDimension;
        this.aspectRatio = aspectRatio;
        focalLength = (sensorDimension / 2) * Trig.cot(fov / 2);
        super("Camera", PropertyType.CAMERA);
    }

    public double getFovl() {
        return fov;
    }

    public double getFocalLength() {
        return focalLength;
    }

    public double getAspectRatio() {
        return aspectRatio;
    }

    public void updateFov(double fov) {
        this.fov = fov;
        focalLength = (sensorDimension / 2) * Trig.cot(Trig.degreeToRad(fov) / 2);
    }

    public Matrix getLocalToWorldMatrix() {
        assert getParent().hasProperty(PropertyType.TRANSFORM) : "Parent object doesn't have a transform";
        Transform transform = (Transform)getParent().findProperty(PropertyType.TRANSFORM);
        return transform.getWorldToLocalMatrix().inverse();
    }

    public Matrix getProjectionMatrix() {
        double value[][] = {
            {focalLength, 0, sensorDimension / 2},
            {0, focalLength, (sensorDimension * aspectRatio) / 2},
            {0, 0, 1}
        };
        Matrix out = new Matrix(3, 3);
        out.val = value;
        return out;
    }

    @Override
    public void initialize() {}

    @Override
    public void tick() {}
}

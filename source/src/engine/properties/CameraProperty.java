package engine.properties;

import engine.libs.math.Matrix;
import engine.libs.math.Trig;
import engine.libs.math.Vector;
import engine.types.Property;

public class CameraProperty extends Property{

    private double fov;
    private double focalLength;
    private double sensorDimension;
    private double aspectRatio;
    private int width;
    private int height;

    public CameraProperty(double fov, int width, int height, double sensorDimension) {
        this.fov = fov;
        this.sensorDimension = sensorDimension;
        this.width = width;
        this.height = height;
        this.aspectRatio = width / height;
        focalLength = (sensorDimension / 2) * Trig.cot(Trig.degreeToRad(fov) / 2);
        super("Camera", PropertyType.CAMERA);
    }

    public double getFov() {
        return fov;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public double getFocalLength() {
        return focalLength;
    }

    public double getAspectRatio() {
        return aspectRatio;
    }

    public double getSensorDimension() {
        return sensorDimension;
    }

    public void updateFov(double fov) {
        this.fov = fov;
        focalLength = (sensorDimension / 2) * Trig.cot(Trig.degreeToRad(fov) / 2);
    }

    public Matrix getProjectionMatrix() {
        double pixelDensity = height / sensorDimension;
        return new Matrix(new double[][]{
            {focalLength * pixelDensity, 0, width / 2},
            {0, focalLength * pixelDensity, height / 2},
            {0, 0, 1}
        }, 3, 3);
    }

    public Matrix getWorldToLocalTransformation() {
        return getParent().transform().getWorldToLocalMatrix();
    }

    public void lookAt(Vector pos) {
        Vector newZ = Vector.sub(pos, getParent().transform().getPosition()).normalized(1);
        Vector newX = Vector.cross3(newZ, Transform.up).normalized(1);
        Vector newY = Vector.cross3(newX, newZ).normalized(1);

        getParent().transform().setxVector(newX);
        getParent().transform().setyVector(newY);
        getParent().transform().setzVector(newZ);
    }

    @Override
    public void initialize() {}

    @Override
    public void tick() {}

    @Override
    public String pack() {
        String propertyString = "{" + "\"" + getName() + "\"" + "," + getType().toString() + "," + getId() + ",[";
        propertyString += fov + ",";
        propertyString += focalLength + ",";
        propertyString += sensorDimension + ",";
        propertyString += aspectRatio + "]}";
        return propertyString;
    }
}

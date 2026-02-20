package engine.libs.math;

public class PolarCoords {

    public static Vector toPolar(Vector v) {
        assert v.getSize() == 2 : "Vector isn't 2-dimensional";
        double r = v.magnitude();
        return new Vector(new double[]{r, Math.atan2(v.val[1], v.val[0])});
    }

    public static Vector toSpherical(Vector v) {
        assert v.getSize() == 3 : "Vector isn't 3-dimensional";
        double r = v.magnitude();
        return new Vector(new double[]{r, Math.acos(v.val[1] / r), Math.atan2(v.val[2], v.val[0])});
    }

    public static Vector toSphericalAt(Vector coords, Vector v) {
        assert v.getSize() == 3 : "Vector isn't 3-dimensional";
        assert coords.getSize() == 3 : "Coords aren't 3-dimensional";
        double r = coords.val[0];
        double t = coords.val[1];
        double p = coords.val[2];
        return new Vector(new double[]{
            v.val[0] * Math.sin(t) * Math.cos(p) + v.val[2] * Math.sin(t) * Math.sin(p) + v.val[1] * Math.cos(t),
            (v.val[0] * Math.cos(t) * Math.cos(p) + v.val[2] * Math.cos(t) * Math.sin(p) + v.val[1] * Math.sin(t)) / r,
            -(v.val[0] * Math.sin(p) - v.val[2] * Math.cos(p)) / r
        });
    }

    public static Vector toCartesian(Vector v) {
        return v;
    }
}

package engine.libs.math;

public class HomogenousCoords {
    
    public static Vector toHomCoords(Vector v, double a) {
        return new Vector(new double[]{
            v.val[0],
            v.val[1],
            v.val[2],
            a
        });
    }

    public static Vector toNormCoords(Vector v) {
        return new Vector(new double[] {
            v.val[0],
            v.val[1],
            v.val[2]
        });
    }
}

package engine.libs.types.TwoGeometry;

import engine.libs.math.Vector;

public class Triangle {
    public Vector A;
    public Vector B;
    public Vector C;

    public Triangle() {
        A = new Vector(2);
        B = new Vector(2);
        C = new Vector(2);
    }
    public Triangle(Vector A, Vector B, Vector C) {
        this.A = A;
        this.B = B;
        this.C = C;
    }
}

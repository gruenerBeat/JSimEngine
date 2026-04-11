package engine.rendering.Utils;

import engine.libs.math.HomogenousCoords;
import engine.libs.math.Matrix;
import engine.libs.math.Vector;
import engine.libs.types.Color.Color;

public class Triangle {
    
    public Vector A;
    public Vector B;
    public Vector C;
    public Color color;

    public Triangle() {}

    public Triangle(Vector A, Vector B, Vector C) {
        this.A = A;
        this.B = B;
        this.C = C;
    }

    public Triangle(Vector A, Vector B, Vector C, Color color) {
        this.A = A;
        this.B = B;
        this.C = C;
        this.color = color;
    }

    public Triangle transform(Matrix transform) {
        Vector AHom = HomogenousCoords.toHomCoords(A, 1);
        Vector BHom = HomogenousCoords.toHomCoords(B, 1);
        Vector CHom = HomogenousCoords.toHomCoords(C, 1);
        A = HomogenousCoords.toNormCoords(transform.act(AHom));
        B = HomogenousCoords.toNormCoords(transform.act(BHom));
        C = HomogenousCoords.toNormCoords(transform.act(CHom));
        return this;
    }

    public Triangle project(Matrix projection) {
        A = projection.act(A);
        B = projection.act(B);
        C = projection.act(C);
        A = new Vector(new double[]{A.val[0], A.val[1]});
        B = new Vector(new double[]{B.val[0], B.val[1]});
        C = new Vector(new double[]{C.val[0], C.val[1]});
        return this;
    }
}

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

    public Triangle transform(Matrix transformation) {
        Vector AHom = HomogenousCoords.toHomCoords(A, 1);
        Vector BHom = HomogenousCoords.toHomCoords(B, 1);
        Vector CHom = HomogenousCoords.toHomCoords(C, 1);

        return new Triangle(
            HomogenousCoords.toNormCoords(transformation.act(AHom)),
            HomogenousCoords.toNormCoords(transformation.act(BHom)),
            HomogenousCoords.toNormCoords(transformation.act(CHom)),
            color
        );
    }

    public Triangle project(Matrix projection) {
        Vector projA = Vector.mul(projection.act(A), 1 / A.val[2]);
        Vector projB = Vector.mul(projection.act(B), 1 / B.val[2]);
        Vector projC = Vector.mul(projection.act(C), 1 / C.val[2]);
        return new Triangle(
            new Vector(new double[]{projA.val[0], projA.val[1]}),
            new Vector(new double[]{projB.val[0], projB.val[1]}),
            new Vector(new double[]{projC.val[0], projC.val[1]}),
            color
        );
    }
}

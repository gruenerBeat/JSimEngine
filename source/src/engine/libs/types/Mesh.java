package engine.libs.types;

import engine.libs.math.Vector;
import engine.libs.types.util.IntegerTriplet;

public class Mesh {
    
    private Vector[] verticies;
    private IntegerTriplet[] triangles;

    public Mesh() {}

    public Mesh(Vector[] verticies, IntegerTriplet[] triangles) {
        this.verticies = verticies;
        this.triangles = triangles;
    }
}

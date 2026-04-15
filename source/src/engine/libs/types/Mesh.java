package engine.libs.types;

import engine.rendering.Utils.Triangle;

public class Mesh {
    
    public Triangle[] tris;

    public Mesh(Triangle[] tris) {
        this.tris = tris;
    }
}

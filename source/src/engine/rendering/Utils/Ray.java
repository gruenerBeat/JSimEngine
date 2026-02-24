package engine.rendering.Utils;

import engine.libs.math.Vector;
import engine.libs.types.Color.Color;

public class Ray {
    
    public Vector origin;
    public Vector direction;
    public double hitDst;
    public Vector hitNormal;
    public Color hitColor;

    public Ray() {
        hitDst = Double.MAX_VALUE;
    }

    public Ray(Vector origin, Vector direction, Color hitColor) {
        this.origin = origin;
        this.direction = direction;
        this.hitColor = hitColor;
        hitDst = Double.MAX_VALUE;
    }
}

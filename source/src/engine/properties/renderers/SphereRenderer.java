package engine.properties.renderers;

import engine.libs.types.Color.Color;
import engine.properties.PropertyType;
import engine.types.Property;

public class SphereRenderer extends Property {
    
    public double radius;
    public Color color;

    public SphereRenderer(double radius, Color color) {
        this.radius = radius;
        this.color = color;
        super("Sphere Renderer", PropertyType.SPHERE_RENDERER);
    }

    @Override
    public void initialize() {}

    @Override
    public void tick() {}
}

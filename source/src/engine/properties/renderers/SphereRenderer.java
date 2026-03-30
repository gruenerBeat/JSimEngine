package engine.properties.renderers;

import engine.libs.types.Material;
import engine.properties.PropertyType;
import engine.types.Property;

public class SphereRenderer extends Property {
    
    public double radius;
    public Material material;

    public SphereRenderer(double radius, Material material) {
        this.radius = radius;
        this.material = material;
        super("Sphere Renderer", PropertyType.SPHERE_RENDERER);
    }

    @Override
    public void initialize() {}

    @Override
    public void tick() {}
}

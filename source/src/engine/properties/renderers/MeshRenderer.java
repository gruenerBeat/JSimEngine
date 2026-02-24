package engine.properties.renderers;

import engine.libs.types.Mesh;
import engine.properties.PropertyType;
import engine.types.Property;

public class MeshRenderer extends Property {

    private Mesh mesh;

    public MeshRenderer() {
        super("Mesh Renderer", PropertyType.MESH_RENDERER);
    }

    public MeshRenderer(Mesh mesh) {
        super("Mesh Renderer", PropertyType.MESH_RENDERER);
        this.mesh = mesh;
    }
    
    @Override
    public void initialize() {
        
    }

    @Override
    public void tick() {
        
    }

    public Mesh getMesh() {
        return mesh;
    }

    public void setMesh(Mesh mesh) {
        this.mesh = mesh;
    }
}

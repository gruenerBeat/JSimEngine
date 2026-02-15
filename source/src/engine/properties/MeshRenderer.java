package engine.properties;

import engine.libs.types.Mesh;
import engine.types.Property;

public class MeshRenderer extends Property {
    
    private Mesh mesh;

    public MeshRenderer() {
        super("Mesh Renderer", PropertyType.MESH_RENDERER);
    }

    @Override
    public void instantiate() {}

    public Mesh localToWorldMesh() {
        //TODO: Transform from local to world coordinates
        return mesh;
    }
}

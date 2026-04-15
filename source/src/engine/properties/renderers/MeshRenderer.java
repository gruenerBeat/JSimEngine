package engine.properties.renderers;

import engine.libs.math.HomogenousCoords;
import engine.libs.math.Matrix;
import engine.libs.types.Mesh;
import engine.properties.PropertyType;
import engine.rendering.Utils.Triangle;
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

    public Mesh getMeshInWorld() {
        Matrix transformation = getParent().transform().getWorldToLocalMatrix().inverse();
        Triangle[] transformed = new Triangle[mesh.tris.length];

        for(int i = 0; i < transformed.length; i++) {
            transformed[i] = new Triangle(
                HomogenousCoords.toNormCoords(transformation.act(HomogenousCoords.toHomCoords(mesh.tris[i].A, 1))),
                HomogenousCoords.toNormCoords(transformation.act(HomogenousCoords.toHomCoords(mesh.tris[i].B, 1))),
                HomogenousCoords.toNormCoords(transformation.act(HomogenousCoords.toHomCoords(mesh.tris[i].C, 1))),
                mesh.tris[i].color
            );
        }
        return new Mesh(transformed);
    }
}

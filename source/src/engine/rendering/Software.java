package engine.rendering;

import engine.libs.math.Vector;
import engine.libs.types.Mesh;
import engine.libs.types.Texture;
import engine.objects.Camera;
import engine.properties.CameraProperty;
import engine.properties.PropertyType;
import engine.properties.renderers.MeshRenderer;
import engine.rendering.Utils.Triangle;
import engine.types.Object;
import engine.types.World;

public class Software extends Renderer {

    private static Software software;

    private Software(int width, int height) {
        super(width, height);
    }

    public static Software getInstance(int width, int height) {
        if(software == null) {
            software = new Software(width, height);
        }
        return software;
    }

    public static Software getInstance() {
        return software;
    }
    
    @Override
    public void setup(Object cam, World world) {}

    @Override
    public Texture render(Object cam, World world) {
        assert cam.hasProperty(PropertyType.CAMERA) : "Object isn't a camera.";
        Texture texture = new Texture(width, height);

        CameraProperty camProp = ((Camera)cam).camera();

        for(int i = 0; i < world.getObjects().size(); i++) {
            Object obj = world.getObjects().get(i);

            if(obj.hasProperty(PropertyType.MESH_RENDERER)) {
                Mesh mesh = ((MeshRenderer)obj.findProperty(PropertyType.MESH_RENDERER)).getMeshInWorld();

                for(int j = 0; j <  mesh.tris.length; j++) {

                    Triangle twoDimensional = mesh.tris[j];

                    twoDimensional = twoDimensional.transform(camProp.getWorldToLocalTransformation()).project(camProp.getProjectionMatrix());

                    int lowerXBound = Math.clamp(Math.min(Math.min((int)twoDimensional.A.val[0], (int)twoDimensional.B.val[0]), (int)twoDimensional.C.val[0]), 0, width);
                    int upperXBound = Math.clamp(Math.max(Math.max((int)twoDimensional.A.val[0], (int)twoDimensional.B.val[0]), (int)twoDimensional.C.val[0]), 0, width);
                    int lowerYBound = Math.clamp(Math.min(Math.min((int)twoDimensional.A.val[1], (int)twoDimensional.B.val[1]), (int)twoDimensional.C.val[1]), 0, height);
                    int upperYBound = Math.clamp(Math.max(Math.max((int)twoDimensional.A.val[1], (int)twoDimensional.B.val[1]), (int)twoDimensional.C.val[1]), 0, height);

                    for(int x = lowerXBound; x < upperXBound; x++) {
                        for(int y = lowerYBound; y < upperYBound; y++) {
                            
                            Vector P = new Vector(new double[]{x, y});

                            if(inTriangle(P, twoDimensional)) {

                                texture.setPixelAt(x, y, twoDimensional.color);
                            }
                        }   
                    }
                }
            }
        }

        return texture;
    }

    @Override
    public void update(Object cam, World world) {}

    private boolean inTriangle(Vector P, Triangle tri) {

        Vector A = tri.A;
        Vector B = tri.B;
        Vector C = tri.C;

        Vector AP = Vector.sub(P, A);
        Vector BP = Vector.sub(P, B);
        Vector CP = Vector.sub(P, C);

        Vector AB = Vector.sub(B, A);
        Vector BC = Vector.sub(C, B);
        Vector CA = Vector.sub(A, C);

        Vector ABOrth = new Vector(new double[]{AB.val[1], -AB.val[0]});
        Vector BCOrth = new Vector(new double[]{BC.val[1], -BC.val[0]});
        Vector CAOrth = new Vector(new double[]{CA.val[1], -CA.val[0]});

        double dotA = Vector.dot(ABOrth, AP);
        double dotB = Vector.dot(BCOrth, BP);
        double dotC = Vector.dot(CAOrth, CP);

        return Math.signum(dotA) == Math.signum(dotB) && Math.signum(dotC) == Math.signum(dotB);
    }
}

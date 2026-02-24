package engine.rendering;

import engine.libs.math.Vector;
import engine.libs.types.Mesh;
import engine.libs.types.Texture;
import engine.libs.types.Color.Color;
import engine.properties.CameraProperty;
import engine.properties.PropertyType;
import engine.properties.Transform;
import engine.properties.renderers.MeshRenderer;
import engine.properties.renderers.SphereRenderer;
import engine.rendering.Utils.Ray;
import engine.types.Object;
import engine.types.World;

import java.util.stream.IntStream;

import engine.libs.math.HomogenousCoords;

public class RayTracing extends Renderer {

    private static RayTracing rayTracing;

    private Ray[][] rays;

    private RayTracing(int width, int height) {
        super(width, height);
        rays = new Ray[width][height];
    }

    public static RayTracing getInstance(int width, int height) {
        if(rayTracing == null) {
            rayTracing = new RayTracing(width, height);
        }
        return rayTracing;
    }

    public static RayTracing getInstance() {
        return rayTracing;
    }

    @Override
    public Texture render(Object cam, World world) {
        assert cam.hasProperty(PropertyType.CAMERA) : "Object isn't a camera";
        Texture tex = new Texture(width, height);

        IntStream.range(0, width).parallel().forEach(x -> {
            IntStream.range(0, height).parallel().forEach(y -> {

                rays[x][y].hitDst = Double.MAX_VALUE;
                rays[x][y].hitColor = new Color(0, 0, 0);

                for(int i = 0; i < world.getObjects().size(); i++) {
                    Object object = world.getObjects().get(i);

                    if(object.hasProperty(PropertyType.MESH_RENDERER)) {
                        Mesh mesh = ((MeshRenderer)object.findProperty(PropertyType.MESH_RENDERER)).getMesh();
                        for(int j = 0; j < mesh.getTris().length / 3; j++) {
                            Vector normal = mesh.getNormals()[i];
                            Vector origin = mesh.getVertices()[i * 3];

                            double denominator = Vector.dot(normal, rays[x][y].direction);

                            if(denominator > 0) {

                                Vector difference = Vector.sub(origin, rays[x][y].origin);
                                double nominator = Vector.dot(difference, normal);

                                double dst = nominator / denominator;

                                if(dst < rays[x][y].hitDst) {
                                    
                                    Vector hitPoint = Vector.add(rays[x][y].origin, Vector.mul(rays[x][y].direction, dst));
                                    
                                    Vector AB = Vector.sub(mesh.getVertices()[i * 3 + 1], origin);
                                    Vector BC = Vector.sub(mesh.getVertices()[i * 3 + 2], mesh.getVertices()[i * 3 + 1]);
                                    Vector CA = Vector.sub(origin, mesh.getVertices()[i * 3 + 2]);

                                    Vector APoint = Vector.sub(hitPoint, origin);
                                    Vector BPoint = Vector.sub(hitPoint, mesh.getVertices()[i * 3 + 1]);
                                    Vector CPoint = Vector.sub(hitPoint, mesh.getVertices()[i * 3 + 2]);

                                    Vector rotatedAB = AB.rotated(normal, -Math.PI / 2);
                                    Vector rotatedBC = BC.rotated(normal, -Math.PI / 2);
                                    Vector rotatedCA = CA.rotated(normal, -Math.PI / 2);

                                    double dotA = Vector.dot(APoint, rotatedAB);
                                    double dotB = Vector.dot(BPoint, rotatedBC);
                                    double dotC = Vector.dot(CPoint, rotatedCA);

                                    if(dotA > 0 && dotB > 0 && dotC > 0) {
                                        
                                        rays[x][y].hitDst = dst;
                                        rays[x][y].hitColor = new Color(255, 255, 255);
                                        rays[x][y].hitNormal = normal;
                                    }
                                }
                            }
                        }
                    }
                    
                    if(object.hasProperty(PropertyType.SPHERE_RENDERER)) {
                        double r = ((SphereRenderer)object.findProperty(PropertyType.SPHERE_RENDERER)).radius;
                        Vector position = object.transform().getPosition();
                        Color sphereColor = ((SphereRenderer)object.findProperty(PropertyType.SPHERE_RENDERER)).color;

                        double a = rays[x][y].direction.magnitude() * rays[x][y].direction.magnitude();
                        double b = 2 *  Vector.dot(rays[x][y].direction, Vector.sub(rays[x][y].origin, position));
                        double c = Vector.sub(rays[x][y].origin, position).magnitude() * Vector.sub(rays[x][y].origin, position).magnitude() - r * r;

                        double d = b * b - 4 * a * c;

                        if(d >= 0) {
                            double dst;

                            if(d == 0) {
                                dst = -(b / 2);
                            } else {
                                double dst1 = (-b + Math.sqrt(d)) / 2;
                                double dst2 = (-b - Math.sqrt(d)) / 2;

                                dst = dst1 < dst2 ? dst1 : dst2;
                            }

                            if(dst < rays[x][y].hitDst) {
                                rays[x][y].hitDst = dst;
                                rays[x][y].hitNormal = Vector.sub(Vector.add(rays[x][y].origin, Vector.mul(rays[x][y].direction, dst)), position).normalized(1);

                                rays[x][y].hitColor = sphereColor;
                            }
                        }
                    }

               }

                tex.setPixelAt(x, y, rays[x][y].hitColor);
            });
        });

        return tex;
    }

    @Override
    public void setup(Object cam, World world) {
        assert cam.hasProperty(PropertyType.CAMERA) : "Object isn't a camera";
        recalcRays(cam, world);
    }


    @Override
    public void update(Object cam, World world) {
        recalcRays(cam, world);
    }

    private void recalcRays(Object cam, World world) {
        Vector camPos = ((Transform)cam.findProperty(PropertyType.TRANSFORM)).getPosition();
        CameraProperty camProp = ((CameraProperty)cam.findProperty(PropertyType.CAMERA));

        for(int i = 0; i < width; i++) {
            for(int j = 0; j < height; j++) {
                Vector screenPoint = new Vector(new double[]{i, j, 1});
                Vector camSpacePoint = HomogenousCoords.toHomCoords(camProp.getProjectionMatrix().inverse().act(screenPoint), 0);
                Vector worldSpacePoint = HomogenousCoords.toNormCoords(camProp.getLocalToWorldMatrix().act(camSpacePoint));
                Vector direction = Vector.sub(worldSpacePoint, camPos).normalized(1);

                rays[i][j] = new Ray(camPos, direction, new Color(0, 0, 0));
            }   
        }
    }
}

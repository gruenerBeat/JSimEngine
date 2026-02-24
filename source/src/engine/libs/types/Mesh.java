package engine.libs.types;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import engine.libs.math.Vector;

public class Mesh {
    
    String name;
    Vector[] vertices;
    int[] tris;
    Vector[] normals;

    public Mesh(int vertexCount, int triCount, String name) {
        vertices = new Vector[vertexCount];
        tris = new int[triCount * 3];
        normals = new Vector[tris.length];
        this.name = name;
    }

    public Mesh(String pathToOBJFIle) {
        File file = new File(pathToOBJFIle);
        Mesh mesh = parse(file);
        name = mesh.name;
        vertices = mesh.vertices;
        normals = mesh.normals;
        tris = mesh.tris;
    }
    
    public Mesh() {

    }

    public Vector[] getVertices() {
        return vertices;
    }

    public int[] getTris() {
        return tris;
    }

    public Vector[] getNormals() {
        return normals;
    }

    public void setVertices(Vector[] vertices) {
        this.vertices = vertices;
    }

    public void setTris(int[] tris) {
        this.tris = tris;
    }

    public void setNormals(Vector[] normals) {
        this.normals = normals;
    }

    public static Mesh parse(File file) {
        assert file.exists() : "No such file: " + file.getPath();

        ArrayList<String> lines = new ArrayList<>();
        Scanner input = null;
        try {
            input = new Scanner(file);
        } catch (Exception e) {}

        while (input.hasNext()) {
            lines.add(input.nextLine());
        }

        input.close();

        Mesh out = new Mesh();
        ArrayList<Vector> verts = new ArrayList<>();
        ArrayList<Vector> norms = new ArrayList<>();
        ArrayList<Integer> tris = new ArrayList<>(); 
        boolean done = false;

        lineloop:
        for(String s : lines) {
            String[] components = s.split(" ");
            switch (components[0]) {
                case "#":
                    break;
                case "mtllib":
                    break;
                case "o":
                    if(!done) {
                        out.name = components[1];
                        done = true;
                    } else {
                        break lineloop;
                    }
                    break;
                case "v":
                    verts.add(new Vector(new double[]{Double.parseDouble(components[1]), Double.parseDouble(components[2]), Double.parseDouble(components[3])}));
                    break;
                case "vt":
                    break;
                case "vn":
                    norms.add(new Vector(new double[]{Double.parseDouble(components[1]), Double.parseDouble(components[2]), Double.parseDouble(components[3])}));
                    break;
                case "usemtl":
                    break;
                case "g":
                    break;
                case "s":
                    break;
                case "f":
                    String[] com1 = components[1].split("/");
                    String[] com2 = components[2].split("/");
                    String[] com3 = components[3].split("/");
                    tris.add(Integer.parseInt(com1[0]));
                    tris.add(Integer.parseInt(com2[0]));
                    tris.add(Integer.parseInt(com3[0]));
                    break;
                default:
                    break;
            }
        }

        out.vertices = new Vector[verts.size()];
        out.normals = new Vector[norms.size()];

        System.arraycopy(verts.toArray(), 0, out.vertices, 0, out.vertices.length);
        System.arraycopy(norms.toArray(), 0, out.normals, 0, out.normals.length);
        out.tris = tris.stream().mapToInt(i -> i).toArray();

        return out;
    }
}

package engine.libs.types;

import java.io.File;

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

        Mesh out = new Mesh();
        //TODO: Make .obj parser

        return out;
    }
}

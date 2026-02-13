package engine.libs.math;

public class Vector {
    
    private int size;
    public double[] val;

    public Vector(int size) {
        this.size = size;
        val = new double[size];
    }

    public int getSize() {
        return size;
    }

    public double magnitude() {
        double sum = 0;
        for(int i = 0; i < size; i++) {
            sum += val[i] * val[i];
        }
        return Math.sqrt(sum);
    }

    public void normalize(double a) {
        double mag = magnitude();
        for(int i = 0; i < size; i++) {
            val[i] *= (a / mag);
        }
    }

    public static double dot(Vector a,  Vector b) {
        if(a.getSize() != b.getSize()) return 0;
        double sum = 0;
        for(int i = 0; i < a.getSize(); i++) {
            sum += a.val[i] * b.val[i];
        }
        return sum;
    }

    public static Vector add(Vector a, Vector b) {
        if(a.getSize() != b.getSize()) return a;
        Vector out = new Vector(a.getSize());
        for(int i = 0; i < a.getSize(); i++) {
            out.val[i] = a.val[i] + b.val[i];
        }
        return out;
    }

    public static Vector sub(Vector a, Vector b) {
        if(a.getSize() != b.getSize()) return a;
        Vector out = new Vector(a.getSize());
        for(int i = 0; i < a.getSize(); i++) {
            out.val[i] = a.val[i] - b.val[i];
        }
        return out;
    }

    public static Vector mul(Vector a, double b) {
        Vector out = new Vector(a.getSize());
        for(int i = 0; i < a.getSize(); i++) {
            out.val[i] = a.val[i] * b;
        }
        return out;
    }

    public static Vector cross3(Vector a, Vector b) {
        if(a.getSize() != b.getSize() || a.getSize() != 3) return a;
        Vector out = new Vector(3);
        out.val[0] = a.val[1] * b.val[2] - a.val[2] * b.val[1];
        out.val[1] = a.val[2] * b.val[0] - a.val[0] * b.val[2];
        out.val[2] = a.val[0] * b.val[1] - a.val[1] * b.val[0];
        return out;
    }

    public static Vector hadamard(Vector a, Vector b) {
        if(a.getSize() != b.getSize()) return a;
        Vector out = new Vector(a.getSize());
        for(int i = 0; i < a.getSize(); i++) {
            out.val[i] = a.val[i] * b.val[i];
        }
        return out;
    }
}

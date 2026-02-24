package engine.libs.math;

public class Vector {
    
    private int size;
    public double[] val;

    public Vector(int size) {
        this.size = size;
        val = new double[size];
    }

    public Vector(double[] val) {
        this.val = val;
        size = val.length;
    }

    public static Vector zero(int size) {
        Vector v = new Vector(size);
        for(int i = 0; i < size; i++) {
            v.val[i] = 0;
        }
        return v;
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

    public Vector normalized(double a) {
        Vector v = new Vector(val);
        double mag = magnitude();
        for(int i = 0; i < size; i++) {
            v.val[i] *= (a / mag);
        }
        return v;
    }

    public Vector rotated(Vector axis, double angle) {
        Vector one = Vector.mul(this, Math.cos(angle));
        Vector two = Vector.mul(Vector.cross3(axis.normalized(1), this), Math.sin(angle));
        Vector three = Vector.mul(axis.normalized(1), Vector.dot(axis.normalized(1), this) * (1 - Math.cos(angle)));
        return Vector.add(one, Vector.add(two, three));
    }


    public static double dot(Vector a,  Vector b) {
        assert a.getSize() == b.getSize() : "Vector dimensions don't match";
        double sum = 0;
        for(int i = 0; i < a.getSize(); i++) {
            sum += a.val[i] * b.val[i];
        }
        return sum;
    }

    public static Vector add(Vector a, Vector b) {
        assert a.getSize() == b.getSize() : "Vector dimensions don't match";
        Vector out = new Vector(a.getSize());
        for(int i = 0; i < a.getSize(); i++) {
            out.val[i] = a.val[i] + b.val[i];
        }
        return out;
    }

    public static Vector sub(Vector a, Vector b) {
        assert a.getSize() == b.getSize() : "Vector dimensions don't match";
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
        assert a.getSize() == b.getSize() : "Vector dimensions don't match";
        assert a.getSize() == 3 : "Vectors aren't 3-dimensional";
        Vector out = new Vector(3);
        out.val[0] = a.val[1] * b.val[2] - a.val[2] * b.val[1];
        out.val[1] = a.val[2] * b.val[0] - a.val[0] * b.val[2];
        out.val[2] = a.val[0] * b.val[1] - a.val[1] * b.val[0];
        return out;
    }

    public static Vector hadamard(Vector a, Vector b) {
        assert a.getSize() == b.getSize() : "Vector dimensions don't match";
        Vector out = new Vector(a.getSize());
        for(int i = 0; i < a.getSize(); i++) {
            out.val[i] = a.val[i] * b.val[i];
        }
        return out;
    }

    @Override
    public String toString() {
        String a = "";
        for(double d : val) {
            a += d + "  ";
        }
        return a;
    }
}

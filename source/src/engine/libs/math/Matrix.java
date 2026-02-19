package engine.libs.math;

public class Matrix {

    private int m;      //Amount of Rows
    private int n;      //Amount of Cols
    public double[][] val;

    public Matrix(int m, int n) {
        val = new double[m][n];
        this.m = m;
        this.n = n;
    }

    public Matrix(double[][] values, int m, int n) {
        val = values;
        this.m = m;
        this.n = n;
    }

    public static Matrix zero(int m, int n) {
        Matrix zero = new Matrix(m, n);
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                zero.val[i][j] = 0;
            }   
        }
        return zero;
    }

    public int getM() {
        return m;
    }

    public int getN() {
        return n;
    }

    public Vector act(Vector v) {
        if(v.getSize() != n) return v;
        Vector out = new Vector(m);
        for(int i = 0; i < m; i++) {
            double sum = 0;
            for(int j = 0; j < n; j++) {
                sum += val[i][j] * v.val[j];
            }
            out.val[i] = sum;
        }
        return out;
    }

    public Matrix act(Matrix mat) {
        if(n != mat.m) return mat;
        Matrix output = new Matrix(m, mat.n);
        for(int i = 0; i < output.m; i++) {
            for(int j = 0; j < output.n; j++) {
                double sum = 0;
                for(int k = 0; k < mat.m; k++) {
                    sum += val[i][k] * mat.val[k][j];
                }
                output.val[i][j] = sum;
            }
        }
        return output;
    }

    //TODO: Write this function
    public Matrix inverse() {
        return this;
    }
}

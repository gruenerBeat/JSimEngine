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

    public Matrix multiply(double a) {
        Matrix out = new Matrix(m, n);
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                out.val[i][j] = a * val[i][j];
            }   
        }
        return out;
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

    public double determinant() {
        if(m != n) return 0;
        if(m == 2) {
            return val[0][0] * val[1][1] - val[0][1] * val[1][0];
        }

        //TODO: Program remaining cases: 3, 4, >4
        return 0;
    }

    
    public Matrix inverse() {
        if(m != n) return this;
        if(m == 2) {
            if(determinant() == 0) return this;
            Matrix mat = new Matrix(new double[][]{
                {val[1][1], -val[0][1]},
                {-val[1][0], val[0][0]}
            }, m, n);
            return mat.multiply(determinant());
        }

        //TODO: Program remaining cases: 3, 4, >4
        return this;
    }
}

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
}

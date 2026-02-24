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

    public Matrix clone() {
        Matrix out = new Matrix(m, n);
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                out.val[i][j] = val[i][j];
            }   
        }
        return out;
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
        assert v.getSize() == n : "Vector dimension doesn't match Matrix dimension";
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
        assert n == mat.m : "Matrix dimensions don't match";
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

    public Matrix transpose() {
        Matrix transpose = new Matrix(n, m);
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
            transpose.val[j][i] = val[i][j];
            }
        }
        return transpose;
    }

    public double determinant() {
        assert m == n : "Can't take determinant of non-square matrix";

        if(m == 2) {
            return val[0][0] * val[1][1] - val[0][1] * val[1][0];
        }

        if(m == 3) {
            return val[0][0] * (val[1][1] * val[2][2] - val[1][2] * val[2][1]) 
                - val[0][1] * (val[1][0] * val[2][2] - val[1][2] * val[2][0])  
                + val[0][2] * (val[1][0] * val[2][1] - val[1][1] * val[2][0]);
        }

        if(m == 4) {
            return val[0][1] * val[1][3] * val[2][2] * val[3][0] -
                    val[0][1] * val[1][2] * val[2][3] * val[3][0] -
                    val[0][0] * val[1][3] * val[2][2] * val[3][1] +
                    val[0][0] * val[1][2] * val[2][3] * val[3][1] -
                    val[0][1] * val[1][3] * val[2][0] * val[3][2] +
                    val[0][0] * val[1][3] * val[2][1] * val[3][2] + val[0][1] * val[1][0] +
                    val[2][3] * val[3][2] - val[0][0] * val[1][1] * val[2][3] * val[3][2] +
                    val[0][3] * (val[1][2] * (val[2][1] * val[3][0] - val[2][0] * val[3][1]) +
                    val[1][1] * (val[2][0] * val[3][2] - val[2][2] * val[3][0]) +
                    val[1][0] * (val[2][2] * val[3][1] - val[2][1] * val[3][2])) +
                    val[0][1] * val[1][2] * val[2][0] * val[3][3] -
                    val[0][0] * val[1][2] * val[2][1] * val[3][3] -
                    val[0][1] * val[1][0] * val[2][2] * val[3][3] +
                    val[0][0] * val[1][1] * val[2][2] * val[3][3] +
                    val[0][2] * (val[1][3] * (val[2][0] * val[3][1] - val[2][1] * val[3][0]) +
                    val[1][1] * (val[2][3] * val[3][0] - val[2][0] * val[3][3]) +
                    val[1][0] * (val[2][1] * val[3][3] - val[2][3] * val[3][1]));
        }

        //TODO: Implement General determinant algorithm
        return 1;
    }

    public Matrix inverse() {
        assert m == n : "Can't form inverse of non-square matrix";
        //assert determinant() != 0 : "Can't form inverse of singular matrix";
        if(determinant() == 0) return this;

        if(m == 2) {
            Matrix out = new Matrix(new double[][]{
                {val[1][1], -val[0][1]},
                {-val[1][0], val[0][0]},
            }, 2, 2);
            return out.multiply(1 / determinant());
        }

        if(m == 3) {
            Matrix out = new Matrix(new double[][]{
                {val[1][1] * val[2][2] - val[2][1] * val[1][2], -val[0][1] * val[2][2] + val[2][1] * val[0][2], val[0][1] * val[1][2] - val[1][1] * val[0][2]},
                {-val[1][0] * val[2][2] + val[2][0] * val[1][2], val[0][0] * val[2][2] - val[2][0] * val[0][2], -val[0][0] * val[1][2] + val[1][0] * val[0][2]},
                {val[1][0] * val[2][1] - val[2][0] * val[1][1], -val[0][0] * val[2][1] + val[2][0] * val[0][1], val[0][0] * val[1][1] - val[1][0] * val[0][1]}
            }, 3, 3);
            return out.multiply(1 / determinant());
        }

        if(m == 4) {
            Matrix out = new Matrix(new double[][]{
                {-val[1][3] * val[2][2] * val[3][1] + val[1][2] * val[2][3] * val[3][1] +
                val[1][3] * val[2][1] * val[3][2] - val[1][1] * val[2][3] * val[3][2] -
                val[1][2] * val[2][1] * val[3][3] + val[1][1] * val[2][2] * val[3][3],
                val[0][3] * val[2][2] * val[3][1] - val[0][2] * val[2][3] * val[3][1] -
                val[0][3] * val[2][1] * val[3][2] + val[0][1] * val[2][3] * val[3][2] +
                val[0][2] * val[2][1] * val[3][3] - val[0][1] * val[2][2] * val[3][3],
                -val[0][3] * val[1][2] * val[3][1] + val[0][2] * val[1][3] * val[3][1] +
                val[0][3] * val[1][1] * val[3][2] - val[0][1] * val[1][3] * val[3][2] -
                val[0][2] * val[1][1] * val[3][3] + val[0][1] * val[1][2] * val[3][3],
                val[0][3] * val[1][2] * val[2][1] - val[0][2] * val[1][3] * val[2][1] -
                val[0][3] * val[1][1] * val[2][2] + val[0][1] * val[1][3] * val[2][2] +
                val[0][2] * val[1][1] * val[2][3] - val[0][1] * val[1][2] * val[2][3]},
                {val[1][3] * val[2][2] * val[3][0] - val[1][2] * val[2][3] * val[3][0] -
                val[1][3] * val[2][0] * val[3][2] + val[1][0] * val[2][3] * val[3][2] +
                val[1][2] * val[2][0] * val[3][3] - val[1][0] * val[2][2] * val[3][3],
                -val[0][3] * val[2][2] * val[3][0] + val[0][2] * val[2][3] * val[3][0] +
                val[0][3] * val[2][0] * val[3][2] - val[0][0] * val[2][3] * val[3][2] -
                val[0][2] * val[2][0] * val[3][3] + val[0][0] * val[2][2] * val[3][3],
                val[0][3] * val[1][2] * val[3][0] - val[0][2] * val[1][3] * val[3][0] -
                val[0][3] * val[1][0] * val[3][2] + val[0][0] * val[1][3] * val[3][2] +
                val[0][2] * val[1][0] * val[3][3] - val[0][0] * val[1][2] * val[3][3],
                -val[0][3] * val[1][2] * val[2][0] + val[0][2] * val[1][3] * val[2][0] +
                val[0][3] * val[1][0] * val[2][2] - val[0][0] * val[1][3] * val[2][2] -
                val[0][2] * val[1][0] * val[2][3] + val[0][0] * val[1][2] * val[2][3]},
                {-val[1][3] * val[2][1] * val[3][0] + val[1][1] * val[2][3] * val[3][0] +
                val[1][3] * val[2][0] * val[3][1] - val[1][0] * val[2][3] * val[3][1] -
                val[1][1] * val[2][0] * val[3][3] + val[1][0] * val[2][1] * val[3][3],
                val[0][3] * val[2][1] * val[3][0] - val[0][1] * val[2][3] * val[3][0] -
                val[0][3] * val[2][0] * val[3][1] + val[0][0] * val[2][3] * val[3][1] +
                val[0][1] * val[2][0] * val[3][3] - val[0][0] * val[2][1] * val[3][3],
                -val[0][3] * val[1][1] * val[3][0] + val[0][1] * val[1][3] * val[3][0] +
                val[0][3] * val[1][0] * val[3][1] - val[0][0] * val[1][3] * val[3][1] -
                val[0][1] * val[1][0] * val[3][3] + val[0][0] * val[1][1] * val[3][3],
                val[0][3] * val[1][1] * val[2][0] - val[0][1] * val[1][3] * val[2][0] -
                val[0][3] * val[1][0] * val[2][1] + val[0][0] * val[1][3] * val[2][1] +
                val[0][1] * val[1][0] * val[2][3] - val[0][0] * val[1][1] * val[2][3]},
                {val[1][2] * val[2][1] * val[3][0] - val[1][1] * val[2][2] * val[3][0] -
                val[1][2] * val[2][0] * val[3][1] + val[1][0] * val[2][2] * val[3][1] +
                val[1][1] * val[2][0] * val[3][2] - val[1][0] * val[2][1] * val[3][2],
                -val[0][2] * val[2][1] * val[3][0] + val[0][1] * val[2][2] * val[3][0] +
                val[0][2] * val[2][0] * val[3][1] - val[0][0] * val[2][2] * val[3][1] -
                val[0][1] * val[2][0] * val[3][2] + val[0][0] * val[2][1] * val[3][2],
                val[0][2] * val[1][1] * val[3][0] - val[0][1] * val[1][2] * val[3][0] -
                val[0][2] * val[1][0] * val[3][1] + val[0][0] * val[1][2] * val[3][1] +
                val[0][1] * val[1][0] * val[3][2] - val[0][0] * val[1][1] * val[3][2],
                -val[0][2] * val[1][1] * val[2][0] + val[0][1] * val[1][2] * val[2][0] +
                val[0][2] * val[1][0] * val[2][1] - val[0][0] * val[1][2] * val[2][1] -
                val[0][1] * val[1][0] * val[2][2] +
                val[0][0] * val[1][1] * val[2][2]}
            }, 4, 4);
            return out.multiply(1 / determinant());
        }

        Matrix tmp = clone();
        Vector p = new Vector(m);
        Matrix inv = new Matrix(m, n);
        tmp.LU(p);
        for(int i = 0; i < n; i++) {
	        Vector b = Vector.zero(n);
	        b.val[i] = 1;
	        tmp.solve(p, b);
	        inv.val[i] = b.val;
	    }
	    return inv.transpose();
    }

    private void LU(Vector p) {
        p = new Vector(m);
        for(int j = 0; j < n; j++) {
            p.val[j] = j;
            double alpha = Math.abs(val[j][j]);
            for(int i = j + 1; i < m; i++) {
                if(Math.abs(val[i][j]) > alpha) {
                    alpha = Math.abs(val[i][j]);
                    p.val[j] = i;
                }
            }
            if(p.val[j] != j) {
                double[] rowJ = val[j];
                val[j] = val[(int)p.val[j]];
                val[(int)p.val[j]] = rowJ;
            }
            for(int i = j + 1; i < m; i++) {
                val[i][j] /= val[j][j];
                for(int l = j + 1; l < n; l++) {
                    val[i][l] -= val[i][j] * val[j][l];
                }
            }
        }
    }

    private void solve(Vector p, Vector b) {
        for(int i = 0; i < m; i++) {
            if(p.val[i] != i) {
                double tmp = b.val[i];
                b.val[i] = b.val[(int)p.val[i]];
                b.val[(int)p.val[i]] = tmp;
            }
        }
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < i; j++) {
                b.val[i] -= val[i][j] * b.val[j];
            }
        }
        for(int i = n - 1; i >= 0; i--) {
            for(int j = i + 1; j < n; j++) {
                b.val[i] -= val[i][j] * b.val[j];
            }
            b.val[i] /= val[i][i];
        }
    }

    @Override
    public String toString() {
        String a = "";
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                a += val[i][j] + "  ";
            }
            a += "\n";
        }
        return a;
    }
}

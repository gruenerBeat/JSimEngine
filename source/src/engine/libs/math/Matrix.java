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

    public void LU(Vector p) {
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

    public void solve(Vector p, Vector b) {
	for(int i = 0; i < m; i++) {
	    if(p.val[i] != i) {
		double tmp = b.val[i];
		b.val[i] = b.val[(int)p.val[i]];
		b.val[(int)p.val[i]] = tmp;
	    }
	}

	//Forward subst (m == n)
	for(int i = 0; i < n; i++) {
	    for(int j = 0; j < i; j++) {
		b.val[i] -= val[i][j] * b.val[j];
	    }
	    //b.val[i] /= A.val[i][i];
	}

	//Backward subst (m == n)
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

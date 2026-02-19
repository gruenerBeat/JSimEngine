package game;

import engine.properties.Behaviour;
import engine.libs.math.Vector;
import engine.libs.math.Matrix;

public class MatrixTest extends Behaviour {

   public MatrixTest() {
       super("MatrixTest");
   } 

   @Override
   public void init() {
	Matrix A = new Matrix(new double[][]{
	    {1, 2, 3},
	    {4, 5, 6},
	    {7, 8, 1}
	}, 3, 3);

	Matrix tmp = A.clone();
	Vector p = new Vector(A.getM()); 
	System.out.println(A.toString());
	System.out.println("\n");
	Matrix aInv = new Matrix(A.getN(), A.getN());

    
	tmp.LU(p);
	System.out.println(tmp.toString());
	System.out.println("\n");
	
	for(int i = 0; i < A.getN(); i++) {
	    Vector b = Vector.zero(A.getN());
	    b.val[i] = 1;
	    tmp.solve(p, b);
	   
	    aInv.val[i] = b.val;
	}
	aInv = aInv.transpose();
   
	System.out.println(aInv.toString()); 
	System.out.println("\n");
	Matrix C = new Matrix(A.getN(), A.getN());
	for(int i = 0; i < A.getN(); i++) {
	    for(int j = 0; j < A.getN(); j++) {
		C.val[i][j] = 0;
		for(int l = 0; l < A.getN(); l++) {
		    C.val[i][j] += A.val[i][l] * aInv.val[l][j];
		}
	    }
	}
	System.out.println(C.toString());
   }

   @Override
   public void run() {
	System.out.println("Test");
   }
}

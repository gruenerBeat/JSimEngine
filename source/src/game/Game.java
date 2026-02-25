package game;
 
import engine.logic.GameRegister;
import engine.types.GameInitializer;
import engine.rendering.RenderType;
import engine.objects.Empty;
import engine.properties.renderers.SphereRenderer;
import engine.libs.types.Color.Color;
import engine.libs.math.Vector;
import engine.types.Object;
import engine.types.World;
import engine.libs.math.Matrix;
import game.MatrixTest;

public class Game extends GameRegister {
    
    @Override
    public GameInitializer register() {
        GameInitializer init = new GameInitializer();
	init.screenWidth = 1280;
	init.screenHeight = 720;
	init.rt = RenderType.RAY_TRACING;

	return init;
    }

    @Override
    public void init() {
   
	Matrix A = new Matrix(new double[][]{
	    {0, 1},
	    {1, 0}
	}, 2, 2);

	System.out.println(A.toString());
	System.out.println("\n");
	System.out.println(MatrixTest.determinant(A));

	Object sphere = new Empty("Kugel");
	sphere.addProperty(new SphereRenderer(1, new Color(127, 0, 0)));
	sphere.transform().setPosition(new Vector(new double[]{0, 0, -3}));
	World.getCurrent().addObject(sphere);
    }
}

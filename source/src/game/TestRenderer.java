package game;

import engine.libs.types.Texture;
import engine.libs.types.Color.Color;
import engine.logic.Main;
import engine.rendering.Renderer;
import engine.types.Object;
import engine.types.World;

public class TestRenderer extends Renderer {
    
    private static TestRenderer testRenderer;

    private TestRenderer(int width, int height) {
        super(width, height);
    }

    public static TestRenderer getInstance(int width, int height) {
        if(testRenderer == null) {
            testRenderer = new TestRenderer(width, height);
        }
        return testRenderer;
    }

    public static TestRenderer getInstance() {
        return testRenderer;
    }

    @Override
    public Texture render(Object cam, World world) {
        Texture texture = new Texture(width, height);
        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
                Color color = new Color(
                    (int)(255 * ((double)x / (double)width)),
                    (int)(255 * ((double)y / (double)height)),
                    (int)(127.5 * Math.sin((Math.TAU / 100) * Main.getFrame() - (Math.PI / 2)) + 127.5)
                );
                texture.setPixelAt(x, y, color);
            }   
        }
        return texture;
    }
}

package engine.rendering;

import engine.libs.types.Texture;
import engine.types.Object;
import engine.types.World;

public abstract class Renderer {

    public int width;
    public int height;

    public Renderer(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public abstract void setup(Object cam, World world);
    public abstract void update(Object cam, World world);
    public abstract Texture render(Object cam, World world);
}

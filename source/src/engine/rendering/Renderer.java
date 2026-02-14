package engine.rendering;

import engine.libs.types.Texture;
import engine.libs.types.Color.Color;
import engine.types.Object;
import engine.types.World;

public abstract class Renderer<T extends Color> {

    public int width;
    public int height;

    public Renderer(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public abstract Texture<T> render(Object cam, World world);
}

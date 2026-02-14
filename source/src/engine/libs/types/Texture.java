package engine.libs.types;

import engine.libs.types.Color.Color;

public class Texture<T extends Color> {
    
    private int width;
    private int height;
    private T[][] pixels;

    @SuppressWarnings("unchecked")
    public Texture(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = (T[][])new Object[width][height];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public T[][] getPixels() {
        return pixels;
    }

    public T getPixelAt(int x, int y) {
        return pixels[x][y];
    }

    public void setPixelAt(int x, int y, T value) {
        pixels[x][y] = value;
    }
}

package engine.libs.types;

import engine.libs.types.Color.Color;

public class Texture {
    
    private int width;
    private int height;
    private Color[][] pixels;

    public Texture(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new Color[width][height];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Color[][] getPixels() {
        return pixels;
    }

    public Color getPixelAt(int x, int y) {
        return pixels[x][y];
    }

    public void setPixelAt(int x, int y, Color value) {
        pixels[x][y] = value;
    }
}

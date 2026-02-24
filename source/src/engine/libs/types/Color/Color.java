package engine.libs.types.Color;

public class Color {
    
    public double r;
    public double g;
    public double b;
    public double a;

    public Color() {};

    public Color(int r, int g, int b) {
        this.r = r < 0 ? 0 : r;
        this.g = g < 0 ? 0 : g;
        this.b = b < 0 ? 0 : b;
    };

    public Color(int r, int g, int b, int a) {
        this.r = r < 0 ? 0 : r;
        this.g = g < 0 ? 0 : g;
        this.b = b < 0 ? 0 : b;
        this.a = a < 0 ? 0 : a;
    };

    public int toRGBInt() {
        return ((int)r << 16) | ((int)g << 8) | (int)b;
    }
}

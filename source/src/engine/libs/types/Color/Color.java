package engine.libs.types.Color;

public class Color {
    
    public double r;
    public double g;
    public double b;
    public double a;

    public Color() {};

    public Color(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    };

    public Color(int r, int g, int b, int a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    };

    public int toRGBInt() {
        return ((int)r << 16) | ((int)g << 8) | (int)b;
    }
}

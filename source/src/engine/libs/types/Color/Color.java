package engine.libs.types.Color;

public class Color {
    
    public double r;
    public double g;
    public double b;

    public Color() {};

    public Color(int r, int g, int b) {
        this.r = r < 0 ? 0 : r;
        this.g = g < 0 ? 0 : g;
        this.b = b < 0 ? 0 : b;
    };

    public int toRGBInt() {
        return ((int)r << 16) | ((int)g << 8) | (int)b;
    }

    public Color mul(double a) {
        r = Math.clamp(a * r, 0, 255);
        g = Math.clamp(a * g, 0, 255);
        b = Math.clamp(a * b, 0, 255);
        return this;
    }

    public Color normalize() {
        double max = Math.max(Math.max(r, g), b);
        r /= (255 / max);
        g /= (255 / max);
        b /= (255 / max);
        return this;
    }

    public static Color add(Color a, Color b) {
        Color c = new Color(
            (int)(a.r + b.r),
            (int)(a.g + b.g),
            (int)(a.b + b.b)
        );
        return c.normalize();
    }
}

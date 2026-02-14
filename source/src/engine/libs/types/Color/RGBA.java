package engine.libs.types.Color;

public class RGBA extends Color {
    
    public double r;
    public double g;
    public double b;
    public double a;

    @Override
    public int toRGBInt() {
        return ((int)r << 16) | ((int)g << 8) | (int)b;
    }
}

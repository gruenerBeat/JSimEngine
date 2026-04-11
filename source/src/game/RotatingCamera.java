package game;

import engine.libs.math.Vector;
import engine.logic.Main;
import engine.properties.Behaviour;
import engine.properties.CameraProperty;
import engine.properties.PropertyType;

public class RotatingCamera extends Behaviour {

    public double frequency;
    public double amplitude;
    public double phase;

    public RotatingCamera(double frequency, double amplitude, double phase) {
        this.amplitude = amplitude;
        this.frequency = frequency;
        this.phase = phase;
        super("RotatingCamera");
    }

    @Override
    public void init() {
        
    }

    @Override
    public void run() {
        getParent().transform().setPosition(new Vector(new double[]{
            amplitude * Math.sin(frequency * (Main.getGameTime() / Main.getTargetTPS()) + phase),
            0,
            amplitude * Math.cos(frequency * (Main.getGameTime() / Main.getTargetTPS()) + phase)
        }));
        ((CameraProperty)getParent().findProperty(PropertyType.CAMERA)).lookAt(Vector.zero(3));
    }
}

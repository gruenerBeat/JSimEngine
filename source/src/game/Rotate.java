package game;

import engine.libs.math.Vector;
import engine.logic.Main;
import engine.properties.Behaviour;

public class Rotate extends Behaviour {
    
    public double speed;

    public Rotate(double speed) {
        this.speed = speed;
        super("Rotate");
    }

    @Override
    public void init() {
    }

    @Override
    public void run() {
        
        getParent().transform().lookAt(new Vector(new double[]{
            Math.sin(speed * (Main.getGameTime() / Main.getActualTPS())),
            0,
            Math.cos(speed * (Main.getGameTime() / Main.getActualTPS()))
        }));
    }
}

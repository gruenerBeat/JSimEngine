package game;

import engine.properties.Behaviour;

public class CustomBehaviour extends Behaviour{
    
    public CustomBehaviour() {
        super("Test Script");
    }

    @Override
    public void init() {
        System.out.println("First TICK!");
    }

    @Override
    public void run() {
        System.out.println("NEW TICK");
    }
}

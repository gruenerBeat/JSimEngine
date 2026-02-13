package engine.properties;

import engine.types.Property;

public abstract class Behaviour extends Property {
    
    public Behaviour(String name) {
        super(name, PropertyType.BEHAVIOUR); 
    }

    public abstract void init();
    public abstract void run();

    @Override
    public void instantiate() {
        init();
    }
}

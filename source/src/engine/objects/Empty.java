package engine.objects;

import engine.properties.Transform;
import engine.types.Object;

public class Empty extends Object{
    
    public Empty(String name) {
        super(name);
        addProperty(new Transform());
    }
}

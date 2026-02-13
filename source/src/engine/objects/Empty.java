package engine.objects;

import engine.properties.Transform;
import engine.types.Object;

public class Empty extends Object{
    
    public Empty() {
        super("Transform");
        properties.add(new Transform(propertyCount));
        propertyCount++;
    }
}

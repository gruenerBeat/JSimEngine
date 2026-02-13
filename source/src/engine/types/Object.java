package engine.types;

import java.util.ArrayList;

public abstract class Object {
    
    public String name;
    public boolean active;

    protected int propertyCount;
    protected ArrayList<Property> properties;

    public Object(String name) {
        propertyCount = 0;
        this.name = name;
        properties = new ArrayList<>();
    }

    public void addProperty(Property p) {
        properties.add(p);
        propertyCount++;
    }

    public Property findProperty(String name) {
        for(Property p : properties) {
            if(p.getName() == name) {
                return p;
            }
        }
        return properties.get(0);
    }

    public Property findProperty(int id) {
        for(Property p : properties) {
            if(p.getId() == id) {
                return p;
            }
        }
        return properties.get(0);
    }

    public void removeProperty(String name) {
        for(Property p : properties) {
            if(p.getName() == name) {
                properties.remove(p);
            }
        }
    }

    public void removeProperty(int id) {
        for(Property p : properties) {
            if(p.getId() == id) {
                properties.remove(p);
            }
        }
    }
}

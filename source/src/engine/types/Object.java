package engine.types;

import java.util.ArrayList;

import engine.properties.PropertyType;
import engine.properties.Transform;


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

    public void addProperty(Property property) {
        if(property.getType() != PropertyType.BEHAVIOUR && hasProperty(property.getType())) return;
        property.setId(propertyCount);
        property.setParent(this);
        properties.add(property);
        propertyCount++;
    }

    public boolean hasProperty(String name) {
        for(Property p : properties) {
            if(p.getName() == name) {
                return true;
            }   
        }
        return false;
    }

    public boolean hasProperty(PropertyType type) {
        for(Property p : properties) {
            if(p.getType() == type) {
                return true;
            }   
        }
        return false;
    }

    public Property findProperty(String name) {
        assert hasProperty(name) : "Cannot find property: " + name + " on object: " + this.name;
        for(Property p : properties) {
            if(p.getName() == name) {
                return p;
            }; 
        }
        return null;
    }

    public Property findProperty(int id) {
        assert propertyCount > id : "Object: " + this.name + " doesn't have a property with id: " + id;
        for(Property p : properties) {
            if(p.getId() == id) {
                return p;
            }; 
        }
        return null;
    }

    public Property findProperty(PropertyType type) {
        assert hasProperty(type) : "Cannot find property: " + type + " on object: " + this.name;
        for(Property p : properties) {
            if(p.getType() == type) {
                return p;
            }; 
        }
        return null;
    }

    public void initialize() {
        for(Property p : properties) {
            p.initialize();
        }
    }

    public void removeProperty(String name) {
        Property property = findProperty(name);
        property.setParent(null);
        properties.remove(property);
        propertyCount--;
    }

    public void removeProperty(int id) {
        Property property = findProperty(id);
        property.setParent(null);
        properties.remove(property);
        propertyCount--;
    }

    public void removeProperty(PropertyType type) {
        Property property = findProperty(type);
        property.setParent(null);
        properties.remove(property);
        propertyCount--;
    }

    public void tick() {
        for(Property p : properties) {
            p.tick();
        }
    }

    public Transform transform() {
        return ((Transform)findProperty(PropertyType.TRANSFORM));
    }
}

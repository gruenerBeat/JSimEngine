package engine.types;

import engine.properties.PropertyType;

public abstract class Property {
    
    private String name;
    private PropertyType type; 
    private int id;
    private Object parent;

    public Property(String name, PropertyType type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public PropertyType getType() {
        return type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Object getParent() {
        return parent;
    }

    public void setParent(Object parent) {
        this.parent = parent;
    }

    public abstract void initialize();
    public abstract void tick();
}

package engine.types;

import java.util.ArrayList;

import engine.objects.Empty;

public class World {

    private static World current;

    public String name;
    private ArrayList<Object> objects;
    
    public World(String name) {
        this.name = name;
    }

    public static World getCurrent() {
        return current;
    }

    public static boolean changeWorld(World w) {
        return false;
    }

    public Object findObject(String name) {
        if(objects.isEmpty()) {
            return new Empty("NULL");
        }
        for(Object obj : objects) {
            if(obj.name == name) {
                return obj;
            }
        }
        return objects.get(0);
    }

    public ArrayList<Object> getObjects() {
        return objects;
    }

    public void addObject(Object obj) {
        objects.add(obj);
        obj.instantiate();
    }

    public void tick() {
        
    }
}

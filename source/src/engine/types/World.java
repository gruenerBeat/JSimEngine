package engine.types;

import java.util.ArrayList;

import engine.logic.Main;
import engine.objects.Empty;

public class World {

    private static World current;

    private boolean active = false;

    public String name;
    private ArrayList<Object> objects;
    
    public World(String name) {
        this.name = name;
        objects = new ArrayList<>();
    }

    public static World getCurrent() {
        return current;
    }

    public static void changeWorld(World w) {
        if(current != null) {
            current.active = false;
        }
        current = w;
        if(Main.isRunning()) {
            current.initialize();
        }
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
        if(active) {
            obj.initialize();
        }
    }

    public void initialize() {
        active = true;
        for (Object obj : objects) {
            obj.initialize();
        }
    }

    public void tick() {
        for (Object obj : objects) {
            obj.tick();
        }
    }
}

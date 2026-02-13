package engine.logic;

import engine.types.World;
import game.Game;

public class Main {
    
    public static boolean running;
    public static long frame;

    public static void main(String[] args) {
        
        if(!Game.game) return;
        Game.start();
        running = true;

        while (running) {
            World.getCurrent().tick();
            frame++;
        }
    }
}

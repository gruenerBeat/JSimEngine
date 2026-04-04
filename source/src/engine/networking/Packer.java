package engine.networking;

import engine.types.World;

public class Packer {
    
    public String packageWorld(World world) {
        return world.pack();
    }

    public World unpackageWorld(byte[] stream) {

        //TODO: Create World unpacker

        return null;
    }
}

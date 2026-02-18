package engine.logic;

import engine.objects.Camera;
import engine.rendering.Renderer;
import engine.types.World;

public class RenderClock extends Thread {

    private Renderer renderer;

    public RenderClock(Renderer renderer) {
        this.renderer = renderer;
    }

    @Override
    public void run() {
        while (Main.isRunning()) {
            try {
                if(Main.getTargetFPS() <= Main.getActualFPS()) {
                    Thread.sleep((int)(1000 / Main.getTargetFPS()));
                } else {
                    Thread.sleep((int)((1000 * Main.getActualFPS()) / Math.pow(Main.getTargetFPS(), 2)));
                }
            } catch (InterruptedException e) {}

            Main.getWindow().Draw(renderer.render(Camera.getCurrent(), World.getCurrent()));
            Main.frameTicked();
        }
    }
}

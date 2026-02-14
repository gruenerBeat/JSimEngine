package engine.logic;

public class RenderClock extends Thread {

    @Override
    public void run() {
        while (Main.isRunning()) {

            Main.frameTicked();
            try {
                Thread.sleep((int)(1000 / Main.getTargetFPS()));
            } catch (InterruptedException e) {}
        }
    }
}

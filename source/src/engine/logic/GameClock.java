package engine.logic;

public class GameClock extends Thread {

    @Override
    public void run() {
        while (Main.isRunning()) {
            System.out.println();
            System.out.println("New Game Tick: " + Main.getGameTime());
            System.out.println("Current FPS is: " + Main.getActualFPS() + ", deltaTime: " + Main.getDeltaTime() + ", targetFPS: " + Main.getTargetFPS());
            System.out.println(Main.getFrame() + " frames have been rendered so far");
            System.out.println();
            Main.gameTicked();
            try {
                Thread.sleep(1000 / Main.getTargetTPS());
            } catch (InterruptedException e) {}
        }
    }
}

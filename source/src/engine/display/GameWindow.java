package engine.display;

import javax.swing.JFrame;
import javax.swing.JPanel;

import engine.libs.types.Texture;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.image.BufferedImage;

public class GameWindow extends JFrame {

    private static GameWindow gameWindow;

    private GameWindow(String name, int width, int height) {
        setTitle(name);
        setSize(width, height);
        setResizable(false);
        setBackground(new Color(0, 0, 0));
        setLocation(200, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());

        add(panel);
        setVisible(true);
    }

    public static GameWindow getInstance(String name, int width, int height) {
        if(gameWindow == null) {
            gameWindow = new GameWindow(name, width, height);
        }
        return gameWindow;
    }

    public static GameWindow getInstance() {
        return gameWindow;
    }

    public void Draw(Texture t) {
        if(t.getWidth() != getWidth() || t.getHeight() != getHeight()) return;

        BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);

        for(int x = 0; x < t.getWidth(); x++) {
            for(int y = 0; y < t.getHeight(); y++) {

                engine.libs.types.Color.Color color = t.getPixelAt(x, y);
                int rgb = color.toRGBInt();
                image.setRGB(x, y, rgb);
            }   
        }

        getGraphics().drawImage(image, 0, 0, this);
    }
}

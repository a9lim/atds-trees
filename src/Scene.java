import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public abstract class Scene {
    protected bPanel pan;
    public Scene(bPanel p){
        pan = p;
    }
    public BufferedImage getImage(String s) {
        try {
            return ImageIO.read(Scene.class.getClassLoader().getResource(s));
        } catch (Exception ex) {
            System.err.println("images broke");
            System.err.println(ex.getStackTrace());
            return null;
        }
    }

    public void advance() {
        pan.advance();
    }

    public abstract void update();

    public abstract void display(Graphics g);

    public abstract void keyPress(KeyEvent e);
    public abstract void keyRelease(KeyEvent e);

}

package GUI;

import Util.OtherUtil;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public abstract class Scene {
    protected final BufferedImage bg;
    protected final bPanel pan;
    public Scene(bPanel p, BufferedImage b){
        pan = p;
        bg = b;
    }
    public Scene(bPanel p, String b){
        pan = p;
        bg = OtherUtil.getImage(b);
    }

    public void advance() {
        pan.advance();
    }

    public abstract void update();

    public abstract void display(Graphics g);

    public abstract void keyPress(KeyEvent e);
    public abstract void keyRelease(KeyEvent e);

}

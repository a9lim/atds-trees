package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class bPanel extends JPanel implements KeyListener {
    private Scene scene;

    private final Scene[] scenes = new Scene[6];

    private int stage;

    public static final int[] SIZE = new int[]{540, 810};

    public bPanel() {
        this.setPreferredSize(new Dimension(SIZE[0], SIZE[1]));

        try {
            stage = 0;
            scenes[0] = new Titlescene(this);
            scenes[1] = new Cutscene(this,new String[]{"I'll be your killer fish for the evening","cringe"}, "fish3.png","Fish:");
            scenes[2] = new Gamescene(this,1);
            scenes[3] = new Cutscene(this,new String[]{"hello","why the long face?"}, "horse2.png","Horse:");
            scenes[4] = new Gamescene(this,3);
            scenes[5] = new Cutscene(this,new String[]{"yippee"}, "fish3.png","Fish:");
            scene = scenes[0];

        } catch (Exception grum) {
            System.err.println("something in this is fucked, email me if you see this");
            System.err.println(grum.getMessage());
        }
    }

    public void update(){
        scene.update();
    }

    public void paint(Graphics g) {
        scene.display(g);
    }

    public void keyPressed(KeyEvent e) {
        scene.keyPress(e);
    }
    public void keyReleased(KeyEvent e){
        scene.keyRelease(e);
    }
    public void keyTyped(KeyEvent e) { }

    public void advance() {
        scene = scenes[stage++];
        if(scene instanceof Gamescene g)
            g.start();
        repaint();
    }
}
enum world {
    TORUS,
    RPP,
    KLEIN,
    SPHERE,
    PASS,
    BOUNCE
}

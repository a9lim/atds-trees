import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.ListIterator;

public class bPanel extends JPanel implements KeyListener {
    private Image bg;
    private Scene scene;

    private Scene[] scenes;

    private int stage;

    public static final int[] SIZE = new int[]{540, 810};

    public bPanel() {
        this.setPreferredSize(new Dimension(SIZE[0], SIZE[1]));

        try {
            stage = 0;
            scenes = new Scene[5];

            String[] words = new String[]{"I'll be your killer fish for the evening","cringe"};
            scenes[0] = new Cutscene(this,words,"fish3.png","Fish:");
            scenes[1] = new Gamescene(this,1);
            words = new String[]{"hello","why the long face?"};
            scenes[2] = new Cutscene(this,words,"horse2.png","Horse:");
            scenes[3] = new Gamescene(this,3);

            words = new String[]{"yippee"};
            scenes[4] = new Cutscene(this,words,"fish3.png","Fish:");
            scene = scenes[3];
            ((Gamescene)(scene)).start();

        } catch (Exception grum) {
            System.err.println("something in this is fucked, email me if you see this");
            System.err.println(grum.getMessage());
        }
    }

    public void grunk(){
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
        if(scene instanceof Gamescene)
            ((Gamescene)scene).start();
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

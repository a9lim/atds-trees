import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.ListIterator;

public class bPanel extends JPanel implements KeyListener {
    private Image bg;
    private LinkedList<Entity> thing;
    private Player player;
    private Enemy enemy;
    private Entity temp;

    public static final int[] SIZE = new int[]{540, 810};

    public bPanel() {
        this.setPreferredSize(new Dimension(SIZE[0], SIZE[1]));

        try {
            thing = new LinkedList<>();
            player = new Player(thing);
            thing.add(new Enemy(player, thing));
//            this.bg = this.getImage("bg.png");

        } catch (Exception grum) {
            System.err.println("something in this is fucked, email me if you see this");
        }
    }

    public void grunk(){
        ListIterator<Entity> it = thing.listIterator();
        while(it.hasNext()) {
            temp = it.next();
            temp.update();
            if(temp.getX() < 0 || temp.getY() < 0 || temp.getX() > bPanel.SIZE[0] || temp.getY() > bPanel.SIZE[1])
                it.remove();
        }
        player.update();
    }

    public void paint(Graphics g) {
        for(Entity t: thing)
            t.paint(g,this);
        player.paint(g,this);
        this.paintComponents(g);
    }

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_LEFT -> player.setVelX(-1);
            case KeyEvent.VK_RIGHT -> player.setVelX(1);
            case KeyEvent.VK_DOWN -> player.setVelY(1);
            case KeyEvent.VK_UP -> player.setVelY(-1);
            case KeyEvent.VK_SHIFT -> player.setSlow(true);
            case KeyEvent.VK_Z -> player.setShoot(true);
        }
    }
    public void keyReleased(KeyEvent e){
        switch (e.getKeyCode()){
            case KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT -> player.setVelX(0);
            case KeyEvent.VK_UP, KeyEvent.VK_DOWN -> player.setVelY(0);
            case KeyEvent.VK_SHIFT -> player.setSlow(false);
            case KeyEvent.VK_Z -> player.setShoot(false);
        }
    }
    public void keyTyped(KeyEvent e) { }

}
enum world {
    TORUS,
    RPP,
    KLEIN,
    SPHERE,
    PASS,
    BOUNCE
}

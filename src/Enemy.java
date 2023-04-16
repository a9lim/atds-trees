import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.util.LinkedList;

public class Enemy extends hEntity implements ActionListener {

    private int[] vel;
    private boolean die;

    private Player p;

    private LinkedList<Entity> th;

    private LinkedList<CircleWave> waves;
    private Timer t;

    public Enemy(Player pl, LinkedList<Entity> thing){
        super("fish.png",150,150,15,100);
        die = false;
        vel = new int[2];
        p = pl;
        th = thing;
        waves = new LinkedList<>();
        t = new Timer(500, this);
        t.start();
    }

    public void setVelX(int u){
        vel[0] = u;
    }
    public void setVelY(int u){
        vel[1] = u;
    }

    public void update(){
        pos[0] += vel[0];
        pos[1] += vel[1];
        if(p.isShoot() && Math.abs(pos[0]-p.pos[0]) < 5 + hbrad) {
            decHealth(1);
            if(health < 0) {
                th.clear();
                t.stop();
            }
        }
    }

    public void fleeb(int x, int y){
        vel[0] = (x-pos[0])/40;
        vel[1] = (y-pos[1])/40;
    }
    public void actionPerformed(ActionEvent e) {
        fleeb((int)(Math.random()*200)+50,(int)(Math.random()*300)+50);
        Behavior<STATE> b = new Behavior<>();
        b.put(new int[]{0,2,5},new STATE[]{STATE.SPIN,STATE.SPEED,STATE.SLOW});
        waves.add(new CircleWave(20,pos[0],pos[1],50,b));
        th.addAll(waves.getLast().getEntities());
        for(Wave w: waves)
            w.update();
    }

    public void paint(Graphics g, ImageObserver ob) {
        if(die) {
            g.setColor(Color.RED);
            g.fillOval(pos[0] - 25, pos[1] - 25, 50, 50);
            g.setColor(Color.BLACK);
        } else {
            super.paint(g, ob);
        }
    }
}

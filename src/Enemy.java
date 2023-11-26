import GUI.Gamescene;
import GUI.bPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.LinkedList;

public abstract class Enemy extends hEntity implements ActionListener {

    protected int[] vel;
    protected boolean die;

    protected Player p;

    protected LinkedList<Entity> th;

    protected LinkedList<Wave> waves;

    protected Gamescene source;

    protected Timer t;

    protected BufferedImage face;

    protected int lives;

    public Enemy(Gamescene sc, String s, String f, int i, String n){
        super(s,150,150,15,i, n);
        die = false;
        vel = new int[2];
        face = getImage(f);
        source = sc;
        p = sc.getPlayer();
        th = sc.getThing();
        waves = new LinkedList<>();
        lives = 1;
    }

    public void setVelX(int u){
        vel[0] = u;
    }
    public void setVelY(int u){
        vel[1] = u;
    }

    public void update(){
        if (pos[0] + vel[0] > bPanel.SIZE[0] || pos[0] + vel[0] < 0){
            vel[0] = -vel[0];
        }
        if (pos[1] + vel[1] > bPanel.SIZE[1] || pos[1] + vel[1] < 0){
            vel[1] = -vel[1];
        }
        pos[0] += vel[0];
        pos[1] += vel[1];
        if(p.isShoot() && Math.abs(pos[0]-p.pos[0]) < 5 + hbrad) {
            decHealth(1);
            if(health < 0) {
                lives--;
                health = sthealth;
            }
            if(lives < 0) {
                th.clear();
                source.advance();
                t.stop();
            }
        }
    }

    public void fleeb(int x, int y){
        vel[0] = (x-pos[0])/40;
        vel[1] = (y-pos[1])/40;
    }

    public void drophone(int x, int y){
        th.add(new vEntity("pl.png",x,y,(p.getX()-x)/50.,(p.getY()-y)/50.,5,STATE.SPEED));
    }

    public void start(){
        t.start();
    }

    public BufferedImage getFace() {
        return face;
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

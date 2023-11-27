package Entity.Conc;

import Entity.Boss;
import Entity.GameEntity;
import Util.OtherUtil;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.LinkedList;


public class Player extends Boss {

    private LinkedList<GameEntity> b;
    private boolean slow;
    private final int[] vel;
    private boolean die;
    private boolean shoot;
    private BufferedImage face;

    public Player(LinkedList<GameEntity> u){
        super("cubo.png",150,750,3,5,"Cubo");
        face = OtherUtil.getImage("cubo2.png");
        b = u;
        slow = false;
        die = false;
        shoot = false;
        vel = new int[2];
    }

    public String getName() {
        return name;
    }

    public void setVelX(int u){
        vel[0] = u;
    }
    public void setVelY(int u){
        vel[1] = u;
    }

    public void update(){
        if(slow) {
            pos[0] += 2*vel[0];
            pos[1] += 2*vel[1];
        }else{
            pos[0] += 3*vel[0];
            pos[1] += 3*vel[1];
        }
        for(GameEntity v: b){
            if(Math.hypot(v.getX()-pos[0], v.getY()-pos[1]) < hbrad + v.getHbrad()) {
                die = true;
            }
        }
    }
    public void setSlow(boolean b){
        slow = b;
    }
    public void setShoot(boolean b){
        shoot = b;
    }
    public boolean isShoot(){
        return shoot;
    }

    public BufferedImage getFace() {
        return face;
    }

    public void paint(Graphics g, ImageObserver ob) {
        if(die) {
            g.setColor(Color.RED);
            g.fillOval(pos[0]-25, pos[1]-25, 50, 50);
            g.setColor(Color.BLACK);
        }
        if(shoot) {
            g.setColor(Color.RED);
            g.fillRect(pos[0]-2,-1,4,pos[1]-dim[1] - 2);
            g.setColor(Color.BLACK);
        }
        g.drawImage(sprite, pos[0]-dim[0], pos[1]-dim[1], ob);
        if(slow) {
            g.setColor(Color.RED);
            g.fillOval(pos[0]-hbrad, pos[1]-hbrad, 2*hbrad, 2*hbrad);
            g.setColor(Color.BLACK);
        }
    }

}

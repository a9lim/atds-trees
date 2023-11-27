package Entity;

import Util.OtherUtil;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public abstract class Boss extends GameEntity {
    protected int health;
    protected final String name;
    protected final int sthealth;
    public Boss(BufferedImage a, int u, int v, int h, int hp, String n){
        super(a,u,v,h);
        sthealth = health = hp;
        name = n;
    }

    public Boss(String a, int u, int v, int h, int hp, String n){
        this(OtherUtil.getImage(a),u,v,h,hp,n);
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void decHealth(int dh) {
        this.health -= dh;
    }
    public void paint(Graphics g, ImageObserver ob) {
        super.paint(g,ob);
        g.setColor(Color.RED);
        g.drawArc(pos[0]-20,pos[1]-20,40,40,90,360*health/sthealth);
        g.setColor(Color.BLACK);
    }
}

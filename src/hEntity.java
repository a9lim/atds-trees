import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public abstract class hEntity extends Entity{
    protected int health;
    protected final int sthealth;
    public hEntity(BufferedImage a, int u, int v, int h, int hp){
        super(a,u,v,h);
        sthealth = health = hp;
    }

    public hEntity(String a, int u, int v, int h, int hp){
        super(a,u,v,h);
        sthealth = health = hp;
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
        g.drawArc(pos[0]-20,pos[1]-20,40,40,90,(int)(360*health/sthealth));
        g.setColor(Color.BLACK);
    }
}

package Entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import static Util.OtherUtil.getImage;

public abstract class GameEntity {
    protected final BufferedImage sprite;
    protected final int[] pos;

    protected final int[] dim;

    protected final int hbrad;

    public GameEntity(BufferedImage a, int u, int v, int h){
        hbrad = h;
        sprite = a;
        pos = new int[]{u,v};
        dim = new int[]{a.getWidth()/2,a.getHeight()/2};
    }

    public GameEntity(String a, int u, int v, int h){
        hbrad = h;
        sprite = getImage(a);
        pos = new int[]{u,v};
        dim = new int[]{sprite.getWidth()/2,sprite.getHeight()/2};
    }

    public abstract void update();

    public Image getSprite() {
        return sprite;
    }

    public int getX() {
        return pos[0];
    }

    public void setX(int x) {
        pos[0] = x;
    }

    public int getY() {
        return pos[1];
    }

    public void setY(int y) {
        pos[1] = y;
    }

    public int getHbrad() {
        return hbrad;
    }

    public void paint(Graphics g, ImageObserver ob) {
        g.drawImage(sprite, pos[0]-dim[0], pos[1]-dim[1], ob);
    }

}



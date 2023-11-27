package Entity;

import GUI.bPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public abstract class Entity {
    protected BufferedImage sprite;
    protected int[] pos;

    protected int[] dim;

    protected int hbrad;

    public Entity(BufferedImage a, int u, int v, int h){
        hbrad = h;
        sprite = a;
        pos = new int[]{u,v};
        dim = new int[]{a.getWidth()/2,a.getHeight()/2};
    }

    public Entity(String a, int u, int v, int h){
        hbrad = h;
        sprite = getImage(a);
        pos = new int[]{u,v};
        dim = new int[]{sprite.getWidth()/2,sprite.getHeight()/2};
    }

    public abstract void update();

    public Image getSprite() {
//        System.out.println(x + " " + y);
        return sprite;
    }

    public void setSprite(BufferedImage sprite) {
        this.sprite = sprite;
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

    public BufferedImage getImage(String s) {
        try {
            return ImageIO.read(bPanel.class.getClassLoader().getResource(s));
        } catch (Exception ex) {
            System.err.println("images broke");
            System.err.println(ex.getStackTrace());
            return null;
        }
    }

    public void paint(Graphics g, ImageObserver ob) {
        g.drawImage(sprite, pos[0]-dim[0], pos[1]-dim[1], ob);
    }

}



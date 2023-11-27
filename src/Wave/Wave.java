package Wave;

import Entity.Behavior;
import Entity.STATE;
import Entity.Bullet;
import GUI.bPanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public abstract class Wave {

    protected Behavior<STATE> be;
    protected int count;
    protected LinkedList<Bullet> entities;
    private STATE temp;

    public Wave(Behavior<STATE> b){
        count = 0;
        be = b;
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
    // integer approximations for trig of a certain radius, increment, and subdivision

    public int sin(int n, int i, double r){
        return (int)(r*Math.sin(2*Math.PI*i/n));
    }
    public int cos(int n, int i, double r){
        return (int)(r*Math.cos(2*Math.PI*i/n));
    }
    public LinkedList<Bullet> getEntities() {
        return entities;
    }
    
    public void update(){
        if((temp = be.get(count++)) != null)
            for(Bullet v: entities)
                v.setState(temp);
    }
}



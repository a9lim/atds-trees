package Wave;

import Entity.Behavior;
import Entity.STATE;
import Entity.vEntity;
import GUI.bPanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public abstract class Wave {

    protected Behavior<STATE> be;
    protected int count;
    protected LinkedList<vEntity> entities;
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
    public int sin(int n, int i, double r){
        return (int)(r*Math.sin(2*Math.PI*i/n));
    }
    public int cos(int n, int i, double r){
        return (int)(r*Math.cos(2*Math.PI*i/n));
    }
    public LinkedList<vEntity> getEntities() {
        return entities;
    }
    public void update(){
        if((temp = be.get(count++)) != null)
            for(vEntity v: entities)
                v.setState(temp);
    }
}



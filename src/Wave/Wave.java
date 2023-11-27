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

    public LinkedList<Bullet> getEntities() {
        return entities;
    }
    
    public void update(){
        if((temp = be.get(count++)) != null)
            entities.forEach(v -> v.setState(temp));
    }
}



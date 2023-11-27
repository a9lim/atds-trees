package Wave;

import Entity.Behavior;
import Entity.STATE;
import Entity.Bullet;

import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class ForwardWave extends Wave {
    public ForwardWave(int n, int y, Behavior<STATE> b){
        super(b);
        entities = new LinkedList<>();
        BufferedImage im = getImage("pl.png");
        for (int i = 0; i < n; i++)
            entities.add(new Bullet(im, i*540/n, y, 10*Math.random()-5, 30 + 10*Math.random()-5,5,b.get(0)));
    }
}
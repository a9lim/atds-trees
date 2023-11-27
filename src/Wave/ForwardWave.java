package Wave;

import Entity.Behavior;
import Entity.STATE;
import Entity.Bullet;
import Util.OtherUtil;

import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class ForwardWave extends Wave {
    public ForwardWave(int n, int y, Behavior<STATE> b){
        super(b);
        entities = new LinkedList<>();
        BufferedImage im = OtherUtil.getImage("pl.png");
        for (int i = 0; i < n; i++)
            entities.add(new Bullet(im, i*540/n, y, OtherUtil.RANDOM.nextDouble(-5,5), OtherUtil.RANDOM.nextDouble(25,35),5,b.get(0)));
    }
}
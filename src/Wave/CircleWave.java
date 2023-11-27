package Wave;

import Entity.Behavior;
import Entity.STATE;
import Entity.Bullet;
import Util.OtherUtil;

import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class CircleWave extends Wave {
    public CircleWave(int n, int x, int y, int r, Behavior<STATE> b){
        super(b);
        entities = new LinkedList<>();
        BufferedImage im = getImage("pl.png");
        for (int i = 0; i < n; i++)
            entities.add(new Bullet(im, x + OtherUtil.cos(n, i, r), y + OtherUtil.sin(n, i, r), OtherUtil.cos(n, i, r), OtherUtil.sin(n, i, r),5,b.get(0)));
    }
}

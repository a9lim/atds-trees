package Wave;

import Entity.Behavior;
import Entity.STATE;
import Entity.Bullet;

import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class CircleWave extends Wave {
    public CircleWave(int n, int x, int y, int r, Behavior<STATE> b){
        super(b);
        entities = new LinkedList<>();
        BufferedImage im = getImage("pl.png");
        for (int i = 0; i < n; i++)
            entities.add(new Bullet(im, x + cos(n, i, r), y + sin(n, i, r), cos(n, i, r), sin(n, i, r),5,b.get(0)));
    }
}

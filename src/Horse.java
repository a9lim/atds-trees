import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.LinkedList;

public class Horse extends Enemy {
    private int counter = 16;
    private boolean flip = true;
    public Horse(Gamescene sc){
        super(sc,"horse.png","horse2.png",350,"Horse");
        t = new Timer(50, this);
    }
    public void actionPerformed(ActionEvent e) {
        if(lives > 1) {
            if (counter++ > 15) {
                fleeb(p.getX() + (int) (Math.random() * 200) - 100, (int) (Math.random() * 200) + 75);
                counter = 0;
            } else if (counter < 13) {
                drophone(pos[0], pos[1]);
            }
        } else {
            if(counter++ > 10) {
                fleeb(250, 100);
                Behavior<STATE> b = new Behavior<>();
                b.put(new int[]{0, 10}, new STATE[]{STATE.SPEED, STATE.GO});
                waves.add(new ForwardWave(30, pos[1]+10, b));
                counter = 0;
            }
        }
        th.addAll(waves.getLast().getEntities());
        for(Wave w: waves)
            w.update();
    }
}
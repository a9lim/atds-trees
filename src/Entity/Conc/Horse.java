package Entity.Conc;

import Entity.Behavior;
import Entity.STATE;
import GUI.Gamescene;
import Util.OtherUtil;
import Wave.ForwardWave;

import java.awt.event.ActionEvent;
import java.util.Random;
import java.util.random.RandomGenerator;

public class Horse extends Enemy {
    private int counter = 16;
    public Horse(Gamescene sc){
        super(sc, "horse.png", "horse2.png",350,"Entity.Conc.Horse");
    }
    public void actionPerformed(ActionEvent e) {
        if(lives > 0) {
            if (counter++ > 15) {
                fleeb(p.getX() + OtherUtil.RANDOM.nextInt(-100,100), OtherUtil.RANDOM.nextInt(75,275));
                counter = 0;
            } else if (counter < 13) {
                drophone(pos[0], pos[1]);
            }
        } else {
            if(counter++ > 10) {
                fleeb(250, 100);
                Behavior<STATE> b = new Behavior<STATE>();
                b.put(new int[]{0, 20}, new STATE[]{STATE.SPEED, STATE.GO});
                waves.add(new ForwardWave(20, pos[1]+10, b));
                counter = 0;
                th.addAll(waves.getLast().getEntities());
            }
        }
        super.actionPerformed(e);
    }
}

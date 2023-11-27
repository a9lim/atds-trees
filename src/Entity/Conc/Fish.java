package Entity.Conc;

import Entity.Behavior;
import Entity.STATE;
import GUI.Gamescene;
import Wave.CircleWave;

import java.awt.event.ActionEvent;

import Util.OtherUtil;

public class Fish extends Enemy{
    private int counter = 11;
    private boolean flip = true;
    public Fish(Gamescene sc){
        super(sc, "fish.png", "fish2.png",350,"Entity.Conc.Fish");
    }
    public void actionPerformed(ActionEvent e) {
        if(lives > 0) {
            if(counter++ > 10) {
                fleeb(p.getX() + OtherUtil.RANDOM.nextInt(-100,100), OtherUtil.RANDOM.nextInt(75,275));
                Behavior<STATE> b = new Behavior<STATE>();
                b.put(new int[]{0, 30, 50, 60}, new STATE[]{STATE.SPIN, STATE.SPEED, STATE.SLOW, STATE.GO});
                waves.add(new CircleWave(30, pos[0], pos[1], 20, b));
                counter = 0;
                th.addAll(waves.getLast().getEntities());
            }
        } else {
            if(counter++ > 4) {
                fleeb(p.getX(), 100);
                Behavior<STATE> b = new Behavior<STATE>();
                b.put(new int[]{0, 10, (flip = !flip) ? 30 : 15}, new STATE[]{STATE.SPIN, STATE.SPEED, STATE.GO});
                waves.add(new CircleWave(30, pos[0], pos[1], 20, b));
                counter = 0;
                th.addAll(waves.getLast().getEntities());
            }
        }
        super.actionPerformed(e);
    }
}

package Entity.Conc;

import Entity.Behavior;
import Entity.STATE;
import GUI.Gamescene;
import Wave.CircleWave;
import Wave.Wave;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class Fish extends Enemy{
    private int counter = 11;
    private boolean flip = true;
    public Fish(Gamescene sc){
        super(sc, "fish.png", "fish2.png",350,"Entity.Conc.Fish");
    }
    public void actionPerformed(ActionEvent e) {
        if(lives > 0) {
            if(counter++ > 10) {
                fleeb(p.getX() + (int) (Math.random() * 200) - 100, (int) (Math.random() * 200) + 75);
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
                if(flip = !flip) {
                    b.put(new int[]{0, 10, 30}, new STATE[]{STATE.SPIN, STATE.SPEED, STATE.GO});
                } else {
                    b.put(new int[]{0, 10, 15}, new STATE[]{STATE.SPIN, STATE.SPEED, STATE.GO});
                }
                waves.add(new CircleWave(30, pos[0], pos[1], 20, b));
                counter = 0;

                th.addAll(waves.getLast().getEntities());
            }
        }
        super.actionPerformed(e);
    }
}

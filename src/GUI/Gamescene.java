package GUI;

import Entity.Conc.Enemy;
import Entity.Conc.Fish;
import Entity.Conc.Horse;
import Entity.Conc.Player;
import Entity.GameEntity;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.ListIterator;

public class Gamescene extends Scene{
    private LinkedList<GameEntity> thing;
    private Player player;
    private Enemy enemy;
    private GameEntity temp;

    public Gamescene(bPanel p, int i){
        super(p, (BufferedImage) null);
        thing = new LinkedList<>();
        player = new Player(thing);
        enemy = switch(i) {
            case 1 ->
                new Fish(this);
            default ->
                new Horse(this);
        };
        thing.add(enemy);
    }
    public void update(){
        ListIterator<GameEntity> it = thing.listIterator();
        while(it.hasNext()) {
            temp = it.next();
            temp.update();
            if(temp.getX() < 0 || temp.getY() < 0 || temp.getX() > bPanel.SIZE[0] || temp.getY() > bPanel.SIZE[1])
                it.remove();
        }
        player.update();
    }

    public void display(Graphics g) {
        for(GameEntity t: thing)
            t.paint(g,pan);
        player.paint(g,pan);
    }

    public void keyPress(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_LEFT -> player.setVelX(-1);
            case KeyEvent.VK_RIGHT -> player.setVelX(1);
            case KeyEvent.VK_DOWN -> player.setVelY(1);
            case KeyEvent.VK_UP -> player.setVelY(-1);
            case KeyEvent.VK_SHIFT -> player.setSlow(true);
            case KeyEvent.VK_Z -> player.setShoot(true);
        }
    }
    public void keyRelease(KeyEvent e){
        switch (e.getKeyCode()){
            case KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT -> player.setVelX(0);
            case KeyEvent.VK_UP, KeyEvent.VK_DOWN -> player.setVelY(0);
            case KeyEvent.VK_SHIFT -> player.setSlow(false);
            case KeyEvent.VK_Z -> player.setShoot(false);
        }
    }

    public LinkedList<GameEntity> getThing() {
        return thing;
    }

    public Player getPlayer() {
        return player;
    }

    public Enemy getEnemy() {
        return enemy;
    }
    public void start(){
        enemy.start();
    }
}

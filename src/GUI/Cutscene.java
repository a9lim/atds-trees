package GUI;

import Util.OtherUtil;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Cutscene extends Scene{
    private final String[] dialog;
    private int i;
    private final BufferedImage player;
    private final BufferedImage enemy;
    private final String name;
    public Cutscene(bPanel p, String[] s, String face, String na){
        super(p, (BufferedImage) null);
        dialog = s;
        i = 0;
        player = OtherUtil.getImage("cubo2.png");
        enemy = OtherUtil.getImage(face);
        name = na;
    }
    public void display(Graphics g){
        if(i%2 == 0) {
            g.drawImage(enemy, 250, 200, pan);
            g.drawImage(player, -350, 300, pan);
            g.setFont(new Font("Sans", Font.BOLD, 24));
            g.drawChars(name.toCharArray(), 0, name.length(), 400, 300);
            g.setFont(new Font("Sans", Font.PLAIN, 14));
            if(i < dialog.length)
                g.drawChars(dialog[i].toCharArray(),0,dialog[i].length(),300,330);
        } else {
            g.drawImage(enemy, 350, 300, pan);
            g.drawImage(player, -250, 300, pan);
            g.setFont(new Font("Sans", Font.BOLD, 24));
            g.drawChars("Cubo:".toCharArray(), 0, 5, 20, 250);
            g.setFont(new Font("Sans", Font.PLAIN, 14));
            if(i < dialog.length)
                g.drawChars(dialog[i].toCharArray(),0,dialog[i].length(),20,280);
        }

    }
    public void update(){}

    public void keyPress(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_Z)
            if(++i>=dialog.length)
                advance();
    }
    public void keyRelease(KeyEvent e){}
}

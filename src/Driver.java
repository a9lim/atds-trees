import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class Driver extends JFrame implements KeyListener, ActionListener {
    private bPanel p;

//    public static void setspeed( int s ){
//        speed = s;
//    }

    public Driver(){
        super();
        p = new bPanel();
        JPanel e = new JPanel();
        e.add(p);
        add(e);
        pack();
        addKeyListener(this);
        new Timer(5, this).start();
        repaint();
    }

    public void repaint(){
        p.repaint();
//        secondary.clear();
//        secscreen.displayOutput(secondary);
        super.repaint();
        Toolkit.getDefaultToolkit().sync();
    }

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {

        }
        repaint();
    }

    public void keyReleased(KeyEvent e) { }

    public void keyTyped(KeyEvent e) { }

    public void actionPerformed(ActionEvent e){
        p.grunk();
        repaint();
    }

    public static void main(String[] args) {
        Driver app = new Driver();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setVisible(true);
    }
}
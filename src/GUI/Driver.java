package GUI;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Driver extends JFrame implements ActionListener {
    private bPanel p;

    public Driver(){
        super();
        p = new bPanel();
        JPanel e = new JPanel();
        e.add(p);
        add(e);
        pack();
        addKeyListener(p);
        new Timer(5, this).start();
        repaint();
        try{
            Clip clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                    Driver.class.getResourceAsStream("gangnamstyle-01.wav"));
            clip.open(inputStream);
            clip.start();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void repaint(){
        p.repaint();
        super.repaint();
        Toolkit.getDefaultToolkit().sync();
    }

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
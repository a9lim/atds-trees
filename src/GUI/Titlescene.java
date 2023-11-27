package GUI;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Titlescene extends Scene {
    public static final String title = "ลูกทุ่งเดธ";
    public Titlescene(bPanel p) {
        super(p,"cover.png");
    }

    public void display(Graphics g) {
        g.drawImage(bg,0,0,pan);
        g.setFont(new Font("Noto Serif Thai", Font.BOLD, 50));
        g.drawChars(title.toCharArray(), 0, title.length(), 60, 600);
        g.setFont(new Font("Noto Sans", Font.BOLD, 40));
        g.drawChars("Project 1st".toCharArray(), 0, 11, 270, 600);
        g.setFont(new Font("Noto Sans", Font.PLAIN, 24));
        g.drawChars("Embodiment of San Diego".toCharArray(), 0, 23, 120, 640);

        g.setFont(new Font("Noto Sans", Font.PLAIN, 14));
        g.drawChars("Press Any Key".toCharArray(), 0, 13, 220, 690);
    }

    public void update() {

    }

    public void keyPress(KeyEvent e) {
        advance();
    }

    public void keyRelease(KeyEvent e) {

    }
}

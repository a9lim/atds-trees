import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class bPanel extends JPanel {
    Image bg;
    Entity thing;

    public bPanel() {
        this.setPreferredSize(new Dimension(540, 810));

        try {
            thing = new fEntity( getImage("pl.png"), 100, 100 );
//            this.bg = this.getImage("bg.png");

        } catch (Exception grum) {
            System.err.println("something in this is fucked, email me if you see this");
        }
    }

    public void grunk(){
        thing.update();
    }

    public Image getImage(String s) throws Exception {
        return ImageIO.read(bPanel.class.getClassLoader().getResource(s));
    }

    public void paint(Graphics g) {
//        g.drawImage(this.bg, 0, 0, this);
        g.drawImage(thing.getSprite(), thing.getX(), thing.getY(), this);
        this.paintComponents(g);
    }
}
enum world {
    TORUS,
    RPP,
    KLEIN,
    SPHERE,
    PASS,
    BOUNCE
}

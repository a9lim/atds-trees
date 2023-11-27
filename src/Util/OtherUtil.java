package Util;

import GUI.bPanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class OtherUtil {
    public static BufferedImage getImage(String s) {
        try {
            return ImageIO.read(bPanel.class.getClassLoader().getResource(s));
        } catch (Exception ex) {
            System.err.println("images broke");
            System.err.println(ex.getStackTrace());
            return null;
        }
    }
}

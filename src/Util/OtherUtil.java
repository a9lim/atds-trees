package Util;

import GUI.bPanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.Random;


public class OtherUtil {

    public static final Random RANDOM = new Random();
    public static BufferedImage getImage(String s) {
        try {
            return ImageIO.read(bPanel.class.getClassLoader().getResource(s));
        } catch (Exception ex) {
            System.err.println("images broke");
            System.err.println(ex.getStackTrace());
            return null;
        }
    }

    // integer approximations for trig of a certain radius, increment, and subdivision

    public static int sin(int n, int i, double r){
        return (int)(r*Math.sin(2*Math.PI*i/n));
    }
    public static int cos(int n, int i, double r){
        return (int)(r*Math.cos(2*Math.PI*i/n));
    }

}

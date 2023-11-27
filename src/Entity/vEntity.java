package Entity;

import java.awt.image.BufferedImage;

public class vEntity extends Entity{
    public static final double dt = 0.05;
    private double[] tpos;
    private double[] vel;

    protected STATE st;
    public vEntity(BufferedImage a, int u, int v, double s, double t, int h, STATE g){
        super(a,u,v,h);
        st = g;
        tpos = new double[]{u,v};
        vel = new double[]{s,t};
    }

    public vEntity(String a, int u, int v, double s, double t, int h, STATE g){
        super(a,u,v,h);
        st = g;
        tpos = new double[]{u,v};
        vel = new double[]{s,t};
    }

    public void update(){
        double[] f = forces();
        vel[0] += f[0] * dt;
        vel[1] += f[1] * dt;

        tpos[0] += vel[0] * dt;
        tpos[1] += vel[1] * dt;

        pos[0] = (int)tpos[0];
        pos[1] = (int)tpos[1];

    }
    public double[] forces() {
        return switch(st) {
            case SLOW -> new double[]{-0.1 * vel[0], -0.1 * vel[1]};
            case SPEED -> new double[]{0.1 * vel[0], 0.1 * vel[1]};
            case SPIN -> new double[]{-vel[1], vel[0]};
            case GO -> new double[2];
        };
    }
    public void setState(STATE i){
        st = i;
    }
}

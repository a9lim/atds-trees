import java.awt.Image;
public class vEntity extends Entity{
    public static final double dt = 0.05;
    private double[] tpos = new double[2];
    private double[] vel = new double[2];
    public vEntity(Image a, int u, int v, double s, double t){
        super(a,u,v);
        tpos[0] = u;
        tpos[1] = v;
        vel[0] = s;
        vel[1] = t;
    }
    public vEntity(Image a, int u, int v){
        super(a,u,v);
        tpos[0] = u;
        tpos[1] = v;
    }

    public void update(){
        pos[0] += vel[0] * dt;
        pos[1] += vel[1] * dt;

        pos[0] += vel[0] * dt;
        pos[1] += vel[1] * dt;

        pos[0] = (int)tpos[0];
        pos[1] = (int)tpos[1];
    }
}

import java.awt.Image;
public class fEntity extends Entity{
    private int t;
    public fEntity(Image a, int u, int v){
        super(a,u,v);
    }

    public void update(){
        t += 0.02;
        pos[0] = 200 + (int)((40+50*t)*Math.cos(t));
        pos[1] = 200 + (int)((40+50*t)*Math.sin(t));
    }
}

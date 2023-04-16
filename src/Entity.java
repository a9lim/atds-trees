import java.awt.Image;
public abstract class Entity {
    protected Image sprite;
    protected int[] pos = new int[2];

    public Entity(Image a, int u, int v){
        sprite = a;
        pos[0] = u;
        pos[1] = v;
    }

    public abstract void update();

    public Image getSprite() {
//        System.out.println(x + " " + y);
        return sprite;
    }

    public void setSprite(Image sprite) {
        this.sprite = sprite;
    }

    public int getX() {
        return pos[0];
    }

    public void setX(int x) {
        pos[0] = x;
    }

    public int getY() {
        return pos[1];
    }

    public void setY(int y) {
        pos[1] = y;
    }
}

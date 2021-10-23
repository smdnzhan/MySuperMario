package ChaoJiMario;

import java.awt.image.BufferedImage;

public class Obstacles {
    private int x,y,type;
    private BufferedImage img;
    private MyBackground mb;

    public Obstacles(int x,int y,int type,MyBackground mb){
        this.x =x;
        this.y = y;
        this.type = type; //障碍物编号
        this.mb = mb;
        img = MyStaticValue.allObstacleImage.get(type);
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public BufferedImage getImg() {
        return img;
    }

    public void setImg(BufferedImage img) {
        this.img = img;
    }

    public MyBackground getMb() {
        return mb;
    }

    public void setMb(MyBackground mb) {
        this.mb = mb;
    }
}

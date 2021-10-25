package ChaoJiMario;

import java.awt.image.BufferedImage;

public class Monster implements Runnable {

    private int x, y;
    private String type;
    private BufferedImage img;
    private MyBackground mb;
    private int index ;
    Monster(int x, int y, String type, MyBackground mb){
        this.x=x;
        this.y=y;
        this.type=type;
        Thread t = new Thread(this);
        t.start();
    }

    public void monsterRun(){
        if (type.equals("flower")){
            index = index==0?1:0;
            this.img=MyStaticValue.allFlowerImage.get(index);
        }
        if (this.type.equals("triangle")){ //暂定
            this.img=MyStaticValue.allTriangleImage.get(0);
        }
        if (this.type.equals("turtle")){
            this.img=MyStaticValue.allTurtleImage.get(0);
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public BufferedImage getImg(){
        return img;
    }

    public void setImg(BufferedImage img) {
        this.img = img;
    }

    public void setMb(MyBackground mb) {
        this.mb = mb;
    }

    public MyBackground getMb() {
        return mb;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public void run() {
        while (true){
            monsterRun();
        }
    }
}

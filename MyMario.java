package ChaoJiMario;

import java.awt.image.BufferedImage;

public class MyMario implements Runnable {
    private int x,y;
    private String status;
    private BufferedImage img = null;
    private MyBackground mb ;
    private Thread t = null;
    private int Xspeed,Yspeed;
    private int index;
    private int uptime=0;

    public MyMario(){
    }

    public MyMario(int x,int y){
        this.x=x;
        this.y=y;
        img = MyStaticValue.allMarioImage_r.get(0);
        status = "stop-right";
    }

    @Override
    public void run() {
        //控制速度 控制图片变量img 此线程并不直接参与画
        while (true){

            boolean onObstacle = false;
            boolean rightGo = true;
            boolean leftGo = true;
//遍历场景内所有障碍物
            for (int i =0;i<mb.getObs().size();i++){
                Obstacles ob =  mb.getObs().get(i);
                //踩在障碍物上
                if(ob.getY()==this.y+60&&(ob.getX()>this.x-60&&ob.getX()<this.x+60)){
                    onObstacle=true;
                }
                //顶到障碍物
                if(ob.getY()>=this.y-60&&ob.getY()<=this.y-60&&(ob.getX()-this.x>60&&ob.getX()<this.x+60)){
                        this.uptime=0;
                }
                //是否可以向右走
                if(ob.getX()==this.x+60&&(ob.getY()>this.y-60&&ob.getY()<this.y+60)){
                    rightGo=false;
                }
                //是否可以向左走
                if(ob.getX()==this.x-60&&(ob.getY()>this.y-60&&ob.getY()<this.y+60)){
                    leftGo=false;
                }
                //底部碰撞
                if(ob.getY()==this.y-60 && (ob.getX()+60>this.x && ob.getX()-60<this.x)){
                    uptime=0;
                }
            }
            //检测是否在障碍物上
            if (uptime==0&&onObstacle){
                if (status.contains("left")){
                    if(Xspeed!=0){
                        status="move-left";
                    }else{
                        status="stop-left";}
                }
                else{
                    //向右判断
                    if (Xspeed!=0){
                        status="move-right";
                    }else
                        status="stop-right";}
            }
            else {
                if (uptime != 0) {
                    uptime--;
                } else {
                    fall();
                }
                this.y += Yspeed;
            }

            if (Xspeed<0&&leftGo||Xspeed>0&&rightGo){
                x+=Xspeed;
            }
            if (x<0){
                x=0;
            }

            if (status.equals("jump-left")){
                this.img=MyStaticValue.allMarioImage_l.get(4);
            }
            if (status.equals("jump-right")){
                this.img=MyStaticValue.allMarioImage_r.get(4);
            }

            if (status.contains("move")){
                index = index==1?2:1; //在两个图片之间切换
            }
            if(status.equals("move-right")){
                this.img=MyStaticValue.allMarioImage_r.get(index);
            }if (status.equals("move-left")){
                this.img=MyStaticValue.allMarioImage_l.get(index);
            }
            if (status.equals("stop-left")){
                this.img=MyStaticValue.allMarioImage_l.get(0);
            }
            if (status.equals("stop-right")){
                this.img=MyStaticValue.allMarioImage_r.get(0);
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void leftMove(){
        Xspeed = -10;
        if (!status.startsWith("jump")){
            status="jump-left";
        }else {
            status="move-left";
        }
    }

    public void rightMove(){
        Xspeed=10;
        if (!status.startsWith("jump")){
            status="jump-right";
        }else{
            status="move-right";
        }
    }

    public void leftStop(){
        Xspeed=0;
        if (!status.startsWith("jump")){
            status="jump-left";
        }else
            status = "stop-left";
    }

    public void rightStop(){
        Xspeed=0;
        if (!status.startsWith("jump")){
            status="jump-right";
        }else
            status = "stop-right";
    }

    public void jump(){
        //if(status.startsWith("jump")){
            if(status.contains("left")){
                status="jump-left";
            }else
                status="jump-right";
            Yspeed=-10; //向上跳跃y值减少
            uptime=8; //跳跃高度
        //}
    }

    public void fall(){
        if (status.contains("left")){
            status="jump-left";
        }else {
            status="jump-right";
        }
        Yspeed=10;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public BufferedImage getImg() {
        return img;
    }

    public void setMb(MyBackground mb) {
        this.mb = mb;
    }

    public void setImg(BufferedImage img) {
        this.img = img;
    }
}

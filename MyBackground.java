package ChaoJiMario;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class MyBackground {
    public static BufferedImage showingImage ;
    private int order ;
    boolean flag=true ;
    private List<Obstacles> obs = new ArrayList<>();
    private List<Monster> mst = new ArrayList<>();

    public MyBackground(){
    }
    public MyBackground(int order,boolean flag, BufferedImage img){
        this.order=order;
        this.flag=flag;
        showingImage = img;

        if (order==1){ //每个图片边长60 窗口横向长1200 所以循环20次
            //表层地面
            for (int i = 0;i<20;i++){
                obs.add(new Obstacles(i*60,530,9,this));
            }
            //下层地面
            for(int i = 0;i<120;i+=60){
                for (int j =0;j<20;j++){
                    obs.add(new Obstacles(j*60,650-i,10,this));
                }
            }

            //障碍物
            for (int i =300;i<=600;i+=60){
                if (i==300||i==420||i==540||i==600){ //普通砖块
                    obs.add(new Obstacles(i,300,0,this));
                }else { //问号砖块
                    obs.add(new Obstacles(i,300,4,this));
                }
            }
            mst.add(new Monster(760,350,"flower",this));
            obs.add(new Obstacles(720,410,8,this));
            obs.add(new Obstacles(780,410,7,this));
            obs.add(new Obstacles(720,470,6,this));
            obs.add(new Obstacles(780,470,5,this));

        }

        }

    public BufferedImage getShowingImage() {
        return showingImage;
    }

    public int getOrder() {
        return order;
    }

    public boolean isFlag() {
        return flag;
    }

    public List<Monster> getMst(){return mst;}

    public List<Obstacles> getObs() {
        return obs;
    }
}

package ChaoJiMario;

import SuperMario.BackGround;
import SuperMario.Mario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GameFrame extends JFrame implements Runnable,KeyListener {
    //读取所有背景图
    private List<MyBackground> all_bg = new ArrayList<>(3);
    //当前背景
    private MyBackground bg;
    private Image offscreen = null;
    public static void main(String[] args) {
        GameFrame gf = new GameFrame();
    }
    private MyMario mm;
    private Thread t=new Thread(this);

    public GameFrame(){
        this.setTitle("玛丽奥");
        this.setSize(1200, 800);
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        this.setLocation((width - 1200) / 2, (height - 800) / 2);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(this);

        MyStaticValue.init();
        for (int i = 1;i<=3;i++){ //添加关卡背景图 1到3
            this.all_bg.add(new MyBackground(i,true,MyStaticValue.bgImage));
        }
        bg = all_bg.get(0);
        mm = new MyMario(10,450);
        mm.setMb(bg);
        Thread mariot = new Thread(mm);
        mariot.start();
        this.setVisible(true);

        repaint();
        t.start();
        //this.getFocusableWindowState();
    }

    @Override
    public void paint(Graphics g) {
        if(offscreen==null){
            offscreen = createImage(1200,800);
        }
        //缓存区画笔
        Graphics gg = offscreen.getGraphics();

        gg.drawImage(bg.getShowingImage().getScaledInstance(1200,800,1),0,0,null);
        Iterator<Obstacles> iter =bg.getObs().iterator();
        while(iter.hasNext()){
            Obstacles ob = iter.next();
            gg.drawImage(ob.getImg(),ob.getX(),ob.getY(),null);
        }
        Iterator<Monster> iter2 =bg.getMst().iterator();
        while (iter2.hasNext()){
            Monster m = iter2.next();
            gg.drawImage(m.getImg(),m.getX(),m.getY(),null);
        }
        gg.drawImage(mm.getImg(),mm.getX(),mm.getY(),null);
        g.drawImage(offscreen,0,0,null);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 39) {
            mm.rightMove();
        }
        if (e.getKeyCode() == 37) {
            mm.leftMove();
        }
        if (e.getKeyCode()==38){
            mm.jump();
            System.out.println("跳跃！");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == 39) {
            mm.rightStop();
        }
        if (e.getKeyCode() == 37) {
            mm.leftStop();
        }
    }


    @Override
    public void run() {
        while (true){
            repaint();
            try {
                Thread.sleep(100);
                if (mm.getX()>=1140){
                    bg=all_bg.get(bg.getOrder());
                    //新地图 把人物画回出生点
                    mm.setMb(bg);
                    mm.setX(10);
                    mm.setY(450);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

package draw;

import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;
import java.util.Vector;

/**
 * @author dzy
 * @version 1.0
 */
public class Draw_Tank extends JFrame{
    private MyPanel mp = null;
    Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        Draw_Tank drawcirle = new Draw_Tank();
    }
    public Draw_Tank()
    {
        int key = scanner.nextInt();
        mp = new MyPanel(key);
        Thread thread = new Thread(mp);
        thread.start();
        this.add(mp);
        this.setSize(1500 , 1000);
        this.addKeyListener(mp);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Recorder.keepRecord();
                System.exit(0);
            }
        });
    }
}
class MyPanel extends JPanel implements KeyListener , Runnable {

    int enemyTankSize = 10;
    Vector<EnemyTank> enemyTanks;
    Vector<Node> nodes;
    Hero hero = new Hero(400 , 400);
    Shot shot = null;
    Vector<Shot> shots = null;
    Vector<Bomb> Bombs = null;
    Image image1 = null;
    Image image2 = null;
    Image image3 = null;
    public MyPanel(int key)
    {
        enemyTanks = new Vector<>();
        Recorder.Set_enemyTanks(enemyTanks);
        Bombs = new Vector<>();
        shots = new Vector<>();
        Vector<Node> nodes = null;
        if(Recorder.continue_last() == false)
        {
            key = 1;
        }

        if(key == 1)
        {
            for(int i = 0 ; i < enemyTankSize ; i ++)
            {
                EnemyTank enemyTank = new EnemyTank(60 * (i + 1), 0 , enemyTanks);
                enemyTank.setDireact(2);
                enemyTanks.add(enemyTank);
                Thread thread = new Thread(enemyTank);
                thread.start();
                enemyTank.shots.add(new Shot(enemyTank.getX() , enemyTank.getY() , enemyTank.getDireact() , 5));
            }
        }
        else
        {
            nodes = Recorder.nodes;
            for(int i = 0 ; i < nodes.size() ; i ++)
            {
                Node node = nodes.get(i);
                EnemyTank enemyTank = new EnemyTank(node.getX() , node.getY() , enemyTanks);
                enemyTank.setDireact(node.getDireact());
                enemyTanks.add(enemyTank);
                Thread thread = new Thread(enemyTank);
                thread.start();
                enemyTank.shots.add(new Shot(enemyTank.getX() , enemyTank.getY() , enemyTank.getDireact() , 5));
            }
        }
        for(int i = 0 ; i < enemyTanks.size() ; i ++)
        {
            EnemyTank enemyTank = enemyTanks.get(i);
            for(int j = 0 ; j < enemyTank.shots.size() ; j ++)
            {
                Thread thread = new Thread(enemyTank.shots.get(j));
                thread.start();
            }
        }
        image1 = Toolkit.getDefaultToolkit().getImage((Panel.class.getResource("/bomb_1.gif")));
        image2 = Toolkit.getDefaultToolkit().getImage((Panel.class.getResource("/bomb_2.gif")));
        image3 = Toolkit.getDefaultToolkit().getImage((Panel.class.getResource("/bomb_3.gif")));
        // 确保图片完全加载
        MediaTracker tracker = new MediaTracker(this);
        tracker.addImage(image1, 0);
        tracker.addImage(image2, 1);
        tracker.addImage(image3, 2);
        try {
            tracker.waitForAll(); // 等待所有图片加载完成
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
       // System.out.println(image1);
        // 播放音乐
        // new AePlayWave("src\\111.wav").start();
    }
    public void showinfo(Graphics g)
    {
        g.setColor(Color.BLACK);
        Font font = new Font("宋体", Font.BOLD, 25);
        g.setFont(font);

        g.drawString("您累计的击毁敌方坦克" , 1020 , 30);
        drawTank(1020 , 60 , g , 0 , 1);
        g.setColor(Color.BLACK);
        g.drawString(Recorder.getNum() + "" , 1090 , 100);
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0 , 0 , 1000 , 750);
        showinfo(g);
        //自己的坦克
        if(hero.is_live == true)
            drawTank(hero.getX() , hero.getY() , g , hero.getDireact() , 0);
//        if(shot != null && shot.is_live == true)  单发子弹
//            g.fill3DRect(shot.getX() , shot.getY() , 2 , 2 , false);
        synchronized (shots)
        {
            for(int i = shots.size() - 1 ; i >= 0 ; i --)
            {
                Shot shot = shots.get(i);
                if(shot.is_live == true)
                    g.fill3DRect(shot.getX() , shot.getY() , 2 , 2 , false);
                else shots.remove(shot);
            }
        }

        //敌人的坦克
        for(int i = 0 ; i < enemyTanks.size() ; i ++)
        {
            EnemyTank enemyTank = enemyTanks.get(i);
            if(enemyTank.is_live == true)
                drawTank(enemyTank.getX() , enemyTank.getY() , g , enemyTank.getDireact() , 1);
            for(int j = 0 ; j < enemyTank.shots.size() ; j ++)
            {
                Shot shot = enemyTank.shots.get(j);
                if(shot != null && shot.is_live == true)
                    g.fill3DRect(shot.getX() , shot.getY() , 2 , 2 , false);
                else enemyTank.shots.remove(shot);
            }
        }
        for(int i = 0 ; i < Bombs.size() ; i ++)
        {
            Bomb bomb = Bombs.get(i);
            if(bomb.getLive_time() > 600)
            {
                g.drawImage(image1 , bomb.getX() , bomb.getY() , 60 , 60 , this);
            }
            else if(bomb.getLive_time() > 300)
            {
                g.drawImage(image2 , bomb.getX() , bomb.getY() , 60 , 60 , this);
            }
            else
            {
                g.drawImage(image3 , bomb.getX() , bomb.getY() , 60 , 60 , this);
            }

            bomb.setLive_time(bomb.getLive_time() - 1);
            if(bomb.getLive_time() <= 0) Bombs.remove(bomb);
        }

    }
    public Boolean HitTank(Tank tank , Shot shot)
    {
        switch (tank.getDireact())
        {
            case 0:
            case 2:
                if(shot.getX() >= tank.getX() && shot.getX() <= tank.getX() + 40 &&
                        shot.getY() >= tank.getY() && shot.getY() <= tank.getY() + 60)
                {
                    shot.is_live = false;
                    tank.is_live = false;
                    Bombs.add(new Bomb(tank.getX() , tank.getY()));
                    return true;
                }
            case 1:
            case 3:
                if(shot.getX() >= tank.getX() && shot.getX() <= tank.getX() + 60 &&
                        shot.getY() >= tank.getY() && shot.getY() <= tank.getY() + 40)
                {
                    shot.is_live = false;
                    tank.is_live = false;
                    Bombs.add(new Bomb(tank.getX() , tank.getY()));
                    return true;
                }
        }
        return false;
    }
    public void drawTank(int x , int y ,Graphics g , int direct , int type)
    {
        switch (type)  // 不同类型不同颜色
        {
            case 0: // 我们坦克
                g.setColor(Color.cyan);
                break;
            case 1:
                g.setColor(Color.yellow);
                break;
        }
        switch (direct)
        {
            case 0:
                g.fill3DRect(x , y , 10 , 60 , false);
                g.fill3DRect(x + 30 , y , 10 , 60 , false);
                g.fill3DRect(x + 10 , y + 10 , 20 , 40 , false);
                g.fillOval(x + 10 , y + 20 , 20 , 20);
                g.drawLine(x + 20 , y + 30 , x + 20 , y);
                break;
            case 1:
                g.fill3DRect(x , y , 60 , 10 , false);
                g.fill3DRect(x , y + 30 , 60 , 10 , false);
                g.fill3DRect(x + 10 , y + 10 , 40 , 20 , false);
                g.fillOval(x + 20 , y + 10 , 20 , 20);
                g.drawLine(x + 30 , y + 20 , x + 60 , y + 20);
                break;
            case 2:
                g.fill3DRect(x , y , 10 , 60 , false);
                g.fill3DRect(x + 30 , y , 10 , 60 , false);
                g.fill3DRect(x + 10 , y + 10 , 20 , 40 , false);
                g.fillOval(x + 10 , y + 20 , 20 , 20);
                g.drawLine(x + 20 , y + 20 , x + 20 , y + 60);
                break;
            case 3:
                g.fill3DRect(x , y , 60 , 10 , false);
                g.fill3DRect(x , y + 30 , 60 , 10 , false);
                g.fill3DRect(x + 10 , y + 10 , 40 , 20 , false);
                g.fillOval(x + 20 , y + 10 , 20 , 20);
                g.drawLine(x + 30 , y + 20 , x  , y + 20);
                break;
            default:
                System.out.println("没有处理");
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
    //当某个键按下，该方法会触发
    @Override
    public void keyPressed(KeyEvent e) {

        //System.out.println((char)e.getKeyCode() + "被按下..");

        //根据用户按下的不同键，来处理小球的移动 (上下左右的键)
        //在java中，会给每一个键，分配一个值(int)
        if(e.getKeyCode() == KeyEvent.VK_S) {//KeyEvent.VK_DOWN就是向下的箭头对应的code
            if(hero.getY() + 60 < 750)
                hero.setY(hero.getY() + 10);
            // 设置方向
            hero.setDireact(2);
        } else if(e.getKeyCode() == KeyEvent.VK_W) {
            if(hero.getY() > 0)
            {
                hero.setY(hero.getY() - 10);
            }
            hero.setDireact(0);
        } else if(e.getKeyCode() == KeyEvent.VK_A) {
            if(hero.getX() > 0)
                hero.setX(hero.getX() - 10);
            hero.setDireact(3);
        } else if(e.getKeyCode() == KeyEvent.VK_D) {
            if(hero.getX() + 60 < 1000)
                hero.setX(hero.getX() + 10);
            hero.setDireact(1);
        }
        else if(e.getKeyCode() == KeyEvent.VK_J)
        {
            if(shots.size() < 5)   // 五颗子弹
            {
                Shot shot = new Shot(hero.getX() , hero.getY() , hero.getDireact() , 5);
                Thread thread = new Thread(shot);
                shots.add(shot);
                thread.start();
            }

        }
    }

    //当某个键释放(松开)，该方法会触发
    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {
        while(true)
        {   // 是否击中敌人
            for(int i = shots.size() - 1 ; i >= 0 ; i --)
            {
                synchronized (shots)
                {
                    Shot shot = null;
                    if(i < shots.size())
                        shot = shots.get(i);
                    if(shot != null)
                    {
                        for(int j = 0 ; j < enemyTanks.size() ; j ++)
                        {
                            synchronized (enemyTanks)
                            {
                                EnemyTank enemyTank = enemyTanks.get(j);
                                if(enemyTank.is_live == true)
                                {
                                    if(HitTank(enemyTank , shot))
                                    {
                                        Recorder.setNum(Recorder.getNum() + 1);
                                    }
                                }
                            }
                        }
                        synchronized (enemyTanks) // 正确的移除vector 元素，倒序防止remove之后自动索引变换 导致 下一次 get 越界
                        {
                            for(int j = enemyTanks.size() - 1 ; j >= 0 ; j --)
                            {
                                EnemyTank enemyTank = enemyTanks.get(j);
                                if(enemyTank.is_live == false)
                                    enemyTanks.remove(enemyTank);
                            }
                        }
                    }
                }

            }
            if(hero.is_live == true)
            {  //是否被击中
                synchronized (this){
                    for(int i = enemyTanks.size() - 1 ; i >= 0  ; i --)
                    {
                        Vector<Shot> shots = enemyTanks.get(i).shots;
                        synchronized (enemyTanks)
                        {
                            if(i < enemyTanks.size())
                                shots = enemyTanks.get(i).shots;
                        }
                        if(shots == null)  continue;
                        synchronized (shots)
                        {
                            for(int j = shots.size() - 1 ; j >= 0 ; j --)
                            {
                                if(j >= shots.size())  continue;
                                Shot shot = shots.get(j);
                                if(shot.is_live)
                                    HitTank(hero , shot);
                            }
                        }

                    }
                }

            }

            repaint();
        }
    }
}

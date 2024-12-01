package draw;

import java.util.Vector;

/**
 * @author dzy
 * @version 1.0
 */
public class EnemyTank extends Tank implements Runnable{
    Vector<Shot> shots = new Vector<>();
    Vector<EnemyTank> enemyTanks = null;
    public EnemyTank(int x, int y , Vector<EnemyTank> enemyTanks) {
        super(x, y);
        this.enemyTanks = enemyTanks;
    }

    @Override
    public void run() {
        while(true)
        {
            if(shots.size() <= 5)
            {
                Shot shot = new Shot(getX() , getY() , getDireact() , 5);
                shots.add(shot);
                Thread thread = new Thread(shot);
                thread.start();
            }
            switch (getDireact())
            {
                case(0):
                    for(int i = 0 ; i < 30 ; i ++)
                    {
                        if(getY() > 0 && Tank.judge_pos(getX() , getY()  , enemyTanks , getDireact() , this))
                            setY(getY() - 2);// 判断某个点是否存在敌方坦克
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case(1):
                    for(int i = 0 ; i < 30 ; i ++)
                    {
                        if(getX() + 60 < 1000 && Tank.judge_pos(getX(), getY() , enemyTanks , getDireact() , this))
                            setX(getX() + 2);
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case(2):
                    for(int i = 0 ; i < 30 ; i ++)
                    {
                        if(getY() + 60 < 750 && Tank.judge_pos(getX() , getY()  , enemyTanks , getDireact() , this))
                            setY(getY() + 2);
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case(3):
                    for(int i = 0 ; i < 30 ; i ++)
                    {
                        if(getX() > 0 && Tank.judge_pos(getX()  , getY() , enemyTanks , getDireact() , this))
                            setX(getX() - 2);
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
            }
            setDireact((int)(Math.random() * 4));
            if(is_live == false)  break;
        }

    }
}

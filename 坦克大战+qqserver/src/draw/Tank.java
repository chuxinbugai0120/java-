package draw;

import java.util.Vector;

/**
 * @author dzy
 * @version 1.0
 */
public class Tank {
    private int x = 100;//坦克的横坐标
    private int y = 100;//坦克的纵坐标
    private int direact = 0;
    boolean is_live = true;

    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
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

    public int getDireact() {
        return direact;
    }

    public void setDireact(int direact) {
        this.direact = direact;
    }

    public static boolean judge_pos(int x , int y , Vector<EnemyTank> enemyTanks , int dir , EnemyTank my)
    {
        for(int i = 0 ; i < enemyTanks.size() ; i ++)
        {
            EnemyTank enemyTank = enemyTanks.get(i);
            if(enemyTank == my)  continue;
            int enemydir = enemyTank.getDireact();
            int xx = enemyTank.getX();
            int yy = enemyTank.getY();
            if(dir == 0)
            {
                if(enemydir == 0 || enemydir == 2)
                {
                    if(x >= xx && x <= xx + 40 && y >= yy && y <= yy + 60)
                    {
                        return false;
                    }
                    if(x + 40 >= xx && x + 40 <= xx + 40 && y >= yy && y <= yy + 60)
                    {
                        return false;
                    }
                }
                else
                {
                    if(x >= xx && x <= xx + 60 && y >= yy && y <= yy + 40)
                    {
                        return false;
                    }
                    if(x + 40 >= xx && x + 40 <= xx + 60 && y >= yy && y <= yy + 40)
                    {
                        return false;
                    }
                }
            }
            else if(dir == 1)
            {
                if(enemydir == 0 || enemydir == 2)
                {
                    if(x + 60 >= xx && x + 60 <= xx + 40 && y >= yy && y <= yy + 60)
                    {
                        return false;
                    }
                    if(x + 60 >= xx && x + 60 <= xx + 40 && y + 40 >= yy && y + 40 <= yy + 60)
                    {
                        return false;
                    }
                }
                else
                {
                    if(x + 60 >= xx && x + 60 <= xx + 60 && y >= yy && y <= yy + 40)
                    {
                        return false;
                    }
                    if(x + 60 >= xx && x + 60 <= xx + 60 && y + 40 >= yy && y + 40 <= yy + 40)
                    {
                        return false;
                    }
                }
            }
            else if(dir == 2)
            {
                if(enemydir == 0 || enemydir == 2)
                {
                    if(x >= xx && x <= xx + 40 && y + 60 >= yy && y + 60 <= yy + 60)
                    {
                        return false;
                    }
                    if(x + 40 >= xx && x + 40 <= xx + 40 && y + 60 >= yy && y + 60 <= yy + 60)
                    {
                        return false;
                    }
                }
                else {
                    if(x  >= xx && x <= xx + 60 && y + 60 >= yy && y + 60 <= yy + 40)
                    {
                        return false;
                    }
                    if(x + 40 >= xx && x + 40 <= xx + 60 && y + 60 >= yy && y + 60 <= yy + 40)
                    {
                        return false;
                    }
                }
            }
            else if(dir == 3)
            {
                if(enemydir == 0 || enemydir == 2)
                {
                    if(x >= xx && x <= xx + 40 && y >= yy && y <= yy + 60)
                    {
                        return false;
                    }
                    if(x >= xx && x <= xx + 40 && y + 40 >= yy && y + 40 <= yy + 60)
                    {
                        return false;
                    }
                }
                else {
                    if(x >= xx && x  <= xx + 60 && y >= yy && y  <= yy + 40)
                    {
                        return false;
                    }
                    if(x >= xx && x <= xx + 60 && y + 40 >= yy && y + 40 <= yy + 40)
                    {
                        return false;
                    }
                }
            }


        }
        return true;
    }

}

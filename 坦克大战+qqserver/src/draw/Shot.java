package draw;

/**
 * @author dzy
 * @version 1.0
 */
public class Shot implements Runnable{
    private int x , y , direact;
    private int speed;
    boolean is_live = false;
    public Shot(int x, int y, int direact, int speed) {
        is_live = true;
        this.direact = direact;
        this.speed = speed;
        switch (direact)
        {
            case 0:
                this.x = x + 20;
                this.y = y;
                break;
            case 1:
                this.x = x + 60;
                this.y = y + 20;
                break;
            case 2:
                this.x = x + 20;
                this.y = y + 60;
                break;
            case 3:
                this.x = x;
                this.y = y + 20;
                break;
        }
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDireact() {
        return direact;
    }

    public void setDireact(int direact) {
        this.direact = direact;
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

    @Override
    public void run() {
        while(true)
        {
            switch (direact)
            {
                case 0:
                    y -= speed;
                    break;
                case 1:
                    x += speed;
                    break;
                case 2:
                    y += speed;
                    break;
                case 3:
                    x -= speed;
                    break;
            }
            if(!(x <= 1000 && y <= 750 && x >= 0 && y >= 0) || is_live == false)
            {
                is_live = false;
                break;
            }

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

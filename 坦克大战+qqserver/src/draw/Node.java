package draw;

/**
 * @author dzy
 * @version 1.0
 */
public class Node {
    private int x , y , direact;

    public Node(int x, int y, int direact) {
        this.x = x;
        this.y = y;
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

    public int getDireact() {
        return direact;
    }

    public void setDireact(int direact) {
        this.direact = direact;
    }
}

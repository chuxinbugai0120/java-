package Interface;

public class Computer {
    public void work(DBinterface dBinterface)
    {
        dBinterface.connect();
        dBinterface.close();
    }
}

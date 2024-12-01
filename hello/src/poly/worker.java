package poly;

public class worker extends Employee{
    public int count = 20;
    public worker(String name, double salary) {
        super(name, salary);
    }

    @Override
    public double getAnnual() {
        return super.getAnnual();
    }

    public void work()
    {
        System.out.println("打工人！！！！！！！！！！！！！！");
    }
}

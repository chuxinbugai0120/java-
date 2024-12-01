package poly;

public class Manager extends Employee{
    double bonus;
    public int count = 15;
    public Manager(String name, double salary , double bonus) {
        super(name, salary);
        this.bonus = bonus;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    @Override
    public double getAnnual() {
        return super.getAnnual() + bonus;
    }
    public void manage(){
        System.out.println("管理人员在管理！！！！！！！！！！！！！！");
    }
}

package poly;

public class Test {
    public static void main(String[] args) {
        Test test = new Test();
        System.out.println(test);

        Manager manager = new Manager("mage" , 5000 , 200000);
        worker worker = new worker("work" , 2500);
        System.out.println(worker);
        //test.showEmpAnnual(manager);
        test.testWort(manager);
    }
    public void showEmpAnnual(Employee e)
    {
        System.out.println(e.count); // 没有动态绑定机制的
        System.out.println(e.getAnnual());
    }
    public void testWort(Employee e)
    {
        if(e instanceof worker)
        {
            ((worker) e).work();
        }
        else if(e instanceof Manager)
        {
            ((Manager) e).manage();
        }
    }
}

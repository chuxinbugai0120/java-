package Interface;

public class Test {
    public static void main(String[] args) {
        Computer computer = new Computer();
        Mysql mysql = new Mysql();
        Oracle oracle = new Oracle();
        computer.work(mysql);
        System.out.println("---------------------");
        computer.work(oracle);
        /*  打印结果
        Mysql 链接成功
        Mysql 关闭成功
        ---------------------
        Oracle 链接成功
        Oracle 关闭成功
         */
    }
}

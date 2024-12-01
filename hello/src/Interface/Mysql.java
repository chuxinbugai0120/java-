package Interface;

public class Mysql implements DBinterface{
    @Override
    public void connect() {
        System.out.println("Mysql 链接成功");
    }

    @Override
    public void close() {
        System.out.println("Mysql 关闭成功");
    }
}

package Interface;

public class Oracle implements DBinterface{
    @Override
    public void connect() {
        System.out.println("Oracle 链接成功");
    }

    @Override
    public void close() {
        System.out.println("Oracle 关闭成功");
    }
}

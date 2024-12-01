package File_;
import org.junit.jupiter.api.Test;

import java.io.*;

/**
 * @author dzy
 * @version 1.0
 */
public class Filecreate {
    public static void main(String[] args) {

    }
    @Test
    public void create01() // 字节流
    {
        String filePath = "e:\\家教时间表.jpg";
        File p_file = new File("e:\\");
        String fileName = "news2.txt";
        File file = new File(p_file, fileName);
        String desPath = "e:\\家教时间表2.jpg";
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        byte[] bit = new byte[1024];
        int readDate;
        try {
            fileInputStream = new FileInputStream(filePath);
            fileOutputStream = new FileOutputStream(desPath);
            while((readDate = fileInputStream.read(bit)) != -1)
            {
                fileOutputStream.write(bit , 0 , readDate);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                fileInputStream.close();
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("拷贝成功");
    }
    @Test
    public void create02()  // 字符流
    {
        String filePath = "E:\\new2.txt";
        //String desPath = "e:\\家教时间表2.jpg";
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        char []buf = new char[8];
        int readDate;

        try {
            fileWriter = new FileWriter(filePath , true);
            bufferedWriter = new BufferedWriter(fileWriter);
            String now = null;
            bufferedWriter.write("哈哈\\n  ");
            bufferedWriter.write("别埃贡噶啊士大夫");
            bufferedWriter.newLine();
            bufferedWriter.write("23123sdasdsd\n  ");

        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("成功");
    }
    @Test
    public void creat03()  // 标准输入输出 与 打印流
    {   // 只用字节流演示，不用字符流 PrintWriter
        try {
            // 使用 FileOutputStream 作为节点流
            FileOutputStream fos = new FileOutputStream("E:\\output.txt");
            // 使用 PrintStream 包装节点流
            PrintStream ps = new PrintStream(fos); // ▲ , 显示器  -> 文件
            // 可以在初始化的时候改变打印流的位置，也可以用setOut改变--
            // System.setOut(新的printStream(需要用构造器指定输出位置，同▲))
            // System.setOut(new PrintStream(fos || 路径也可以));
            // 此时System.out.println 就不在与显示器绑定了
            ps.write("别逗我笑".getBytes()); // println 的底层方法
            ps.println("Hello, World!");
            // 关闭流 , 关闭的其实还是节点流，关闭时写入
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 需要注意，用  PrintWriter 的时候， PrintWriter的构造器也有 OutputStream
        // 所以也可以用 new PrintWriter(System.out【OutputStream】)
    }


}
abstract class re{
    public abstract void f();
}
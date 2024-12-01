package network;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author dzy
 * @version 1.0
 */
public class TCPClient { // 字节流 / 字节流（转换流） 交互模板
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(InetAddress.getLocalHost() , 9999);
        System.out.println("客户端 sock = " + socket.getClass());
        OutputStream outputStream = socket.getOutputStream();
        String filepath = "src/9.两种字符串创建方式.png";
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(filepath));
        int now;
        byte[] bytes = new byte[1024];
        while((now = bufferedInputStream.read(bytes)) != -1)
        {
            outputStream.write(bytes , 0 , now);
        }
        socket.shutdownOutput();
        InputStream inputStream = socket.getInputStream();
        byte[] bytes2 = new byte[1024];
        int len = inputStream.read(bytes);
        System.out.println(new String(bytes , 0 , len));
        inputStream.close();
        bufferedInputStream.close();
        socket.close();
        outputStream.close();
        System.out.println("客户端退出");
    }
}

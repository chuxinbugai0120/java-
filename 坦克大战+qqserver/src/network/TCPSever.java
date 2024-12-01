package network;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author dzy
 * @version 1.0
 */
public class TCPSever { // 字节流
    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(9999);
        System.out.println("开始监听");
        Socket socket = serverSocket.accept();
        InputStream input = socket.getInputStream();
        String filepath = "src/新图片.png";
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(filepath));
        byte[] bytes = new byte[1024];
        int len ;
        while((len = input.read(bytes)) != -1)
        {
            bufferedOutputStream.write(bytes , 0 , len);
        }
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("收到图片".getBytes());
        outputStream.close();
        socket.shutdownOutput();
        bufferedOutputStream.close();
        serverSocket.close();
        socket.close();

        System.out.println("服务段结束");
    }
}

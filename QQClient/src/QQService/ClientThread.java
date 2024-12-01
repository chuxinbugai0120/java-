package QQService;

import qq_common.Message;
import qq_common.MessageType;

import java.io.*;
import java.net.Socket;
import java.nio.channels.SocketChannel;

/**
 * @author dzy
 * @version 1.0
 */
public class ClientThread extends Thread{
    // 持有 Socket

    private Socket socket;

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public ClientThread(Socket socket)
    {
        this.socket = socket;
    }

    @Override
    public void run() {
        // 因为 Thread 需要在后台和服务器通信，我们用 while
        while(true)
        {
            System.out.println("客户端线程，等待服务器的数据");
            try {
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Message message = (Message)ois.readObject(); // 如果没收到服务器消息，线程会阻塞
                if(message.getMesType().equals(MessageType.MESSAGE_RET_ONLIN_FRIEND))
                {
                    String[] s = message.getContent().split(" "); // 所有的在线用户列表
                    for(int i  = 0 ; i < s.length ; i ++)
                    {
                        System.out.println("用户 : " + s[i]);
                    }
                }
                else if(message.getMesType().equals(MessageType.MESSAGE_COMM_MES))
                {
                    System.out.println("\n" + message.getSender() + " 对 " + message.getGetter() + " 说 " + message.getContent());
                }
                else if(message.getMesType().equals(MessageType.MESSAGE_COMM_MES_ALL))
                {
                    System.out.println("\n" + message.getSender() + " 对 " + " 所有人 " + " 说 " + message.getContent());
                }
                else if(message.getMesType().equals(MessageType.MESSAGE_FILE))
                {
                    System.out.println(message.getGetter() + "收到一个文件");
                    byte[] bytes = new byte[1024 * 64];
                    bytes = message.getFileBytes();
                    FileOutputStream fileOutputStream = new FileOutputStream("src/来自" + message.getSender() + "的图片.png");
                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
                    bufferedOutputStream.write(bytes , 0 , message.getFileLen());
                    bufferedOutputStream.close();
                }
                else
                {
                    System.out.println("其他类型 message");
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }



        }

    }
}

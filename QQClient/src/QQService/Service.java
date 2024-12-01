package QQService;

import qq_common.Message;
import qq_common.MessageType;
import qq_common.User;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author dzy
 * @version 1.0
 * 用户登录验证 和 注册等功能
 */
public class Service {

    private User u = new User(); // 其他地方可能会用 user 属性，所以做成成员
    private Socket socket;
    public boolean checkUser(String userId , String pwd){
        boolean b = false;  // 是否登录成功标记
        u.setUserId(userId);
        u.setPasswd(pwd);
        try {
            socket = new Socket(InetAddress.getLocalHost(), 9999);
            // 发送 user
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(u);
            // 读取 message
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            Message message = (Message)objectInputStream.readObject();
            if(message.getMesType().equals(MessageType.MESSAGE_LOGIN_SUCCEED))
            {
                b = true;
                // 创建一个和服务器保持通信的线程
                ClientThread clientThread = new ClientThread(socket);
                clientThread.start();
                // 为了客户端扩展，把线程放入集合管理
                ManageClient_Thread.addClient_Thread(userId , clientThread);
            }
            else
            {
                // 登陆失败
                socket.close();
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return b;
    }

    public void onlineFriendList()
    {
        // 发生一个 message
        Message message = new Message();
        message.setSender(u.getUserId());
        message.setMesType(MessageType.MESSAGE_GET_ONLIN_FRIEND);
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void sendFile(String geter)
    {
        BufferedInputStream bufferedInputStream = null;
        try {
            FileInputStream fileInputStream = new FileInputStream("src/字符串体系图.png");
            bufferedInputStream = new BufferedInputStream(fileInputStream);
            int len;
            byte[] bytes = new byte[1024 * 64];
            String ans = "";
            bufferedInputStream.read(bytes);
            // 发生一个 message
            Message message = new Message();
            message.setFileLen((int)new File("src/字符串体系图.png").length());
            message.setSender(u.getUserId());
            message.setFileBytes(bytes);
            message.setMesType(MessageType.MESSAGE_FILE);
            message.setContent(ans);
            message.setGetter(geter);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                bufferedInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    public void logout(){
        Message message = new Message();
        message.setMesType(MessageType.MESSAGE_CLIENT_EXIT);
        message.setSender(u.getUserId());
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(ManageClient_Thread.getClient_Thread(u.getUserId()).getSocket().getOutputStream());
            objectOutputStream.writeObject(message);
            objectOutputStream.flush();
            System.out.println(u.getUserId() + " 退出系统");
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

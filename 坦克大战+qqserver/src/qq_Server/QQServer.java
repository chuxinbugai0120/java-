package qq_Server;

import qq_common.Message;
import qq_common.MessageType;
import qq_common.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Vector;

/**
 * @author dzy
 * @version 1.0
 */
public class QQServer {
    public static void main(String[] args) {
        new QQServer();
    }
    private ServerSocket ss = null;
    private static HashMap<String , User> validUsers = new HashMap<>();

    static {
        validUsers.put("100" , new User("100" , "123456"));
        validUsers.put("200" , new User("200" , "123456"));
        validUsers.put("至尊宝" , new User("至尊宝" , "123456"));
    }

    private boolean checkUser(String userId , String passwd)
    {
        User user = validUsers.get(userId);
        if(user == null)  return false;
        if(!(user.getPasswd().equals(passwd)))  return false;
        return true;

    }

    public QQServer()
    {
        try {
            System.out.println("服务器开始监听");
            ss = new ServerSocket(9999);
            new Thread(new SendNewsToAll()).start();
            while(true)
            {
                Socket socket = ss.accept();
                ObjectInputStream objectInputStream
                        = new ObjectInputStream(socket.getInputStream());
                User user = (User) objectInputStream.readObject();
                Message message = new Message();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                // 验证
                if(checkUser(user.getUserId() , user.getPasswd()))
                {
                    message.setMesType(MessageType.MESSAGE_LOGIN_SUCCEED);
                    objectOutputStream.writeObject(message);
                    Server_Thread server_thread = new Server_Thread(socket , user.getUserId());
                    server_thread.start(); // 启动线程
                    // 放入集合管理
                    Manage_Threads.addClientThread(user.getUserId() , server_thread);
                    try {
                        if(Message_left_database.get_Message(user.getUserId()) != null)
                        {
                            Vector<Message> messages = Message_left_database.get_Message(user.getUserId());
                            for(int i = 0 ; i < messages.size() ; i ++)
                            {
                                message = messages.get(i);
                                ObjectOutputStream objectOutputStream2 = new ObjectOutputStream(Manage_Threads.get_Server_Thread(user.getUserId()).getSocket().getOutputStream());
                                objectOutputStream2.writeObject(message); // 把所有的留言加载到用户端
                            }
                            Message_left_database.remove(user.getUserId());
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else
                {
                    message.setMesType(MessageType.MESSAGE_LOGIN_FAIL);
                    System.out.println("ID = " + user.getUserId() + " 登录失败");
                    objectOutputStream.writeObject(message);
                    socket.close();
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            // 服务器退出 while ， 说明不再监听
            try {
                ss.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}

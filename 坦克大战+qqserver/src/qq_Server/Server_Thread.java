package qq_Server;

import qq_common.Message;
import qq_common.MessageType;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Set;

/**
 * @author dzy
 * @version 1.0
 * 该类与某个客户端通讯
 */
public class Server_Thread extends Thread{

    private Socket socket;
    private String userId;  // 连接到服务端的用户ID

    public Server_Thread(Socket socket, String userId) {
        this.socket = socket;
        this.userId = userId;
    }

    public Socket getSocket() {
        return socket;
    }

    @Override
    public void run() {

        while(true)
        {
            System.out.println(userId + "保持通讯，读取数据");
            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                Message message = (Message)objectInputStream.readObject();
                System.out.println(message.getSender() + " " + message.getMesType());
                if(message.getMesType().equals(MessageType.MESSAGE_GET_ONLIN_FRIEND))
                {
                    String Content = Manage_Threads.getOnlineUser();
                    Message message2 = new Message();
                    message2.setMesType(MessageType.MESSAGE_RET_ONLIN_FRIEND);
                    message2.setContent(Content);
                    message2.setSender(message.getSender());
                    System.out.println(message.getSender() + " 请求了在线用户列表");
                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                    oos.writeObject(message2);
                }
                else if(message.getMesType().equals(MessageType.MESSAGE_COMM_MES))
                {
                    if(Manage_Threads.get_Server_Thread(message.getGetter()) != null)
                    {
                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(Manage_Threads.get_Server_Thread(message.getGetter()).getSocket().getOutputStream());
                        objectOutputStream.writeObject(message); // 如果不在线，可以保存到数据库，实现离线留言
                    }
                    else  // 可以用一个HashMap 模拟数据库，来存每一个 id 对应的所有的消息集合
                    {
                        Message_left_database.add(message.getGetter(), message);
                    }
                }
                else if(message.getMesType().equals(MessageType.MESSAGE_COMM_MES_ALL))
                {
                    HashMap hashMap = Manage_Threads.getHm();
                    Set<String> set = hashMap.keySet();
                    for(String now_id : set)
                    {
                        if(now_id != message.getSender())
                        {
                            ObjectOutputStream objectOutputStream = new ObjectOutputStream(Manage_Threads.get_Server_Thread(now_id).getSocket().getOutputStream());
                            objectOutputStream.writeObject(message); // 如果不在线，可以保存到数据库，实现离线留言
                        }
                    }

                }
                else if(message.getMesType().equals(MessageType.MESSAGE_CLIENT_EXIT))
                {
                    System.out.println(message.getSender() + " 退出客户端");
                    Manage_Threads.remove(message.getSender());
                    socket.close();
                    break;
                }
                else if(message.getMesType().equals(MessageType.MESSAGE_FILE))
                {
                    String File_ = message.getContent();
                    System.out.println(message.getSender() + " 正在传输文件给" + message.getGetter());
                    String to_id = message.getGetter();
                   //System.out.println(message.getContent());
                    if(Manage_Threads.get_Server_Thread(message.getGetter()) != null)
                    {
                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(Manage_Threads.get_Server_Thread(to_id).getSocket().getOutputStream());
                        objectOutputStream.writeObject(message); // 如果不在线，可以保存到数据库，实现离线留言
                    }
                    else  // 可以用一个HashMap 模拟数据库，来存每一个 id 对应的所有的消息集合
                    {
                        Message_left_database.add(message.getGetter(), message);
                    }
                }

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
    }
}

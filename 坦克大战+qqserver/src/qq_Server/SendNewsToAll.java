package qq_Server;

import qq_common.Message;
import qq_common.MessageType;
import utils.Utility;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

/**
 * @author dzy
 * @version 1.0
 */
public class SendNewsToAll implements Runnable{

    @Override
    public void run() {
        while(true)
        {
            System.out.println("请输入服务器要推送的消息[输入exit 表示退出推送]");
            String now = Utility.readString(1000);
            if(now.equals("exit"))  break;
            Message message = new Message();
            message.setSender("服务器");
            message.setContent(now);
            message.setMesType(MessageType.MESSAGE_COMM_MES_ALL);
            message.setSendTime(new Date().toString());
            System.out.println("服务器推送消息给所有人 : " + now);
            HashMap hashMap = Manage_Threads.getHm();
            Set<String> set = hashMap.keySet();
            try {
                for(String now_id : set)
                {
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(Manage_Threads.get_Server_Thread(now_id).getSocket().getOutputStream());
                    objectOutputStream.writeObject(message); // 如果不在线，可以保存到数据库，实现离线留言
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

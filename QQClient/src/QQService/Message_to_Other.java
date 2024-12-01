package QQService;

import qq_common.Message;
import qq_common.MessageType;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;

/**
 * @author dzy
 * @version 1.0
 * 与发消息相关的方法
 */
public class Message_to_Other {
    /**
     *
     * @param content  内容
     * @param senderId  发送用户ID
     * @param getterId  接受用户ID
     */
    public void senMessageToOne(String content , String senderId , String getterId)
    {
        Message message = new Message();
        message.setSender(senderId);
        message.setGetter(getterId);
        message.setContent(content);
        message.setMesType(MessageType.MESSAGE_COMM_MES);
        message.setSendTime(new Date().toString());
        System.out.println(senderId + " 对 " + getterId + " 说 " + content);
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(ManageClient_Thread.getClient_Thread(senderId).getSocket().getOutputStream());
            objectOutputStream.writeObject(message );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void senMessageToEvery(String content , String senderId)
    {
        Message message = new Message();
        message.setSender(senderId);
        message.setContent(content);
        message.setMesType(MessageType.MESSAGE_COMM_MES_ALL);
        //message.setSendTime(new Date().toString());
        System.out.println(senderId + " 对 " + "所有人" + " 说 " + content);
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(ManageClient_Thread.getClient_Thread(senderId).getSocket().getOutputStream());
            objectOutputStream.writeObject(message );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

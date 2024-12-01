package qq_Server;

import qq_common.Message;
import sun.misc.resources.Messages;

import java.util.HashMap;
import java.util.Set;
import java.util.Vector;

/**
 * @author dzy
 * @version 1.0
 */
public class Message_left_database {
    private static HashMap<String , Vector<Message>> hm = new HashMap<>();

    public static void add(String userId , Message message)
    {
        if(hm.containsKey(userId) == false)  // 需要先判断 当前有没有 存在这样一个消息队列，不存在的话，需要 new 出来
        {
            hm.put(userId , new Vector<>());
        }
        hm.get(userId).add(message);
    }
    public static Vector<Message> get_Message(String userId)
    {
        return hm.get(userId);
    }

    public static void remove(String userId)
    {
        hm.remove(userId);
    }

    public static HashMap<String, Vector<Message>> getHm() {
        return hm;
    }
}

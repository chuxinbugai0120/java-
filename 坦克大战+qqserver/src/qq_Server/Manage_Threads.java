package qq_Server;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author dzy
 * @version 1.0
 * 该类用于管理 和 客户端通讯的 线程
 */
public class Manage_Threads {
    private static HashMap<String , Server_Thread> hm = new HashMap<>();

    public static void addClientThread(String userId , Server_Thread server_thread)
    {
        hm.put(userId , server_thread);
    }
    public static Server_Thread get_Server_Thread(String userId)
    {
        return hm.get(userId);
    }
    public static void remove(String userId)
    {
        hm.remove(userId);
    }
    public static String getOnlineUser()
    {
        Set<String> strings = hm.keySet();
        String ans = "";
        for(String now: strings)
        {
            ans = ans + now + " ";
        }
        return ans;
    }

    public static HashMap<String, Server_Thread> getHm() {
        return hm;
    }
}

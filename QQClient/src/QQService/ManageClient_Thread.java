package QQService;

import java.util.HashMap;

/**
 * @author dzy
 * @version 1.0
 */
public class ManageClient_Thread {
    private static HashMap<String , ClientThread> hm = new HashMap<>();

    public static void addClient_Thread(String userId , ClientThread clientThread)
    {
        hm.put(userId , clientThread);
    }
    public static ClientThread getClient_Thread(String userId)
    {
        return hm.get(userId);
    }
}

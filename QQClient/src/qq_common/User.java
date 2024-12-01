package qq_common;

import java.io.Serializable;

/**
 * @author dzy
 * @version 1.0
 */
public class User implements Serializable {


    private String userId;
    private String passwd;

    public User()
    {

    }

    public User(String userId, String passwd) {
        this.userId = userId;
        this.passwd = passwd;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
}

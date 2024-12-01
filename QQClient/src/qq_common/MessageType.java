package qq_common;

/**
 * @author dzy
 * @version 1.0
 */
public interface MessageType {
    String MESSAGE_LOGIN_SUCCEED = "1"; // 登录成功
    String MESSAGE_LOGIN_FAIL = "2"; // 登录失败
    String MESSAGE_COMM_MES = "3"; // 信息
    String MESSAGE_GET_ONLIN_FRIEND = "4"; // 获取在线用户列表
    String MESSAGE_RET_ONLIN_FRIEND = "5"; // 返回用户列表
    String MESSAGE_CLIENT_EXIT = "6"; // 客户端请求退出
    String MESSAGE_COMM_MES_ALL = "7";
    String MESSAGE_FILE = "8";
    String MESSAGE_SERVE_TO_ALL = "9"; //客户端说的话
}

package qq_view;

import QQService.Message_to_Other;
import QQService.Service;
import utils.Utility;

/**
 * @author dzy
 * @version 1.0
 * 客服端的界面
 */
public class QQView {
    private boolean loop = true;
    private String key = " ";
    private Service service;
    private Message_to_Other message_to_other;

    public static void main(String[] args) {
        QQView qqView = new QQView();
        qqView.mainMenu();
        System.out.println("客户端退出系统");
    }
    private void mainMenu() {
        // 主菜单
        while(loop)
        {
            System.out.println("================欢迎登录网络通信系统-============");
            System.out.println("\t\t 1 登录系统");
            System.out.println("\t\t 9 退出系统");
            key = Utility.readString(1);
            switch (key)
            {
                case "1":
                    System.out.print("请输入用户号: ");
                    String userId = Utility.readString(50);
                    System.out.print("请输入密  码: ");
                    String pwd = Utility.readString(50);
                    service = new Service();
                    message_to_other = new Message_to_Other();
                    if(service.checkUser(userId , pwd))
                    {
                        System.out.println("\n===========欢迎 (用户 " + userId + " 登录成功) ===========");
                        while(loop)
                        {
                            System.out.println("\n=========网络通信系统二级菜单(用户 " + userId + " )=======");
                            System.out.println("\t\t 1 显示在线用户列表");
                            System.out.println("\t\t 2 群发消息");
                            System.out.println("\t\t 3 私聊消息");
                            System.out.println("\t\t 4 发送文件");
                            System.out.println("\t\t 9 退出系统");
                            System.out.print("请输入你的选择: \n");
                            key = Utility.readString(1);
                            switch (key)
                            {
                                case "1":
                                    System.out.println("显示在线用户列表");
                                    service.onlineFriendList();
                                    break;
                                case "2":
                                    System.out.println("请输入想说的话");
                                    String content = Utility.readString(100);
                                    message_to_other.senMessageToEvery(content , userId);
                                    break;
                                case "3":
                                    System.out.print("请输入想聊天的在线用户号：");
                                    String getterId = Utility.readString(50);
                                    System.out.println("请输入想说的话");
                                    String content2 = Utility.readString(100);
                                    message_to_other.senMessageToOne(content2 , userId , getterId);
                                    System.out.println("私聊消息");
                                    break;
                                case "4":
                                    System.out.println("请输入要发送给的用户名字");
                                    String id = Utility.readString(50);
                                    service.sendFile(id);
                                    break;
                                case "9":
                                    service.logout();
                                    loop = false;
                                    break;
                            }
                        }
                    }
                    else {
                        System.out.println("登录失败");
                    }
                    break;
                case "9":
                    loop = false;
                    break;
            }
        }
    }
}

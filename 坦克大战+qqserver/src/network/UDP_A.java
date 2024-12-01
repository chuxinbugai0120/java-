package network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * @author dzy
 * @version 1.0
 */
public class UDP_A {
    public static void main(String[] args) throws IOException {
        DatagramSocket datagramSocket = new DatagramSocket(9999);
        byte[] bytes = new byte[1024];
        DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length);
        System.out.println("A 等待 数据");
        datagramSocket.receive(datagramPacket);
        System.out.println(new String(datagramPacket.getData() , 0 , datagramPacket.getLength()));
        // datagramSocket.close();

        byte[] bytes2 = "我不好".getBytes();
        DatagramPacket datagramPacket2 = new DatagramPacket(bytes2 , bytes2.length , InetAddress.getLocalHost() , 9988);
        datagramSocket.send(datagramPacket2);
        datagramSocket.close();


    }
}

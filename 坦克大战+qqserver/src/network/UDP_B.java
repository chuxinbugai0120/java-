package network;

import java.io.IOException;
import java.net.*;

/**
 * @author dzy
 * @version 1.0
 */
public class UDP_B {
    public static void main(String[] args) throws IOException {
        DatagramSocket datagramSocket = new DatagramSocket(9988);
        byte[] bytes = "你好啊".getBytes();
        DatagramPacket datagramPacket = new DatagramPacket(bytes , bytes.length , InetAddress.getLocalHost() , 9999);
        datagramSocket.send(datagramPacket);
        // datagramSocket.close();
        byte[] bytes2 = new byte[1024];
        DatagramPacket datagramPacket2 = new DatagramPacket(bytes2, bytes2.length);
        datagramSocket.receive(datagramPacket2);
        System.out.println(new String(datagramPacket2.getData() , 0 , datagramPacket2.getLength()));

    }
}

package File_;

import org.junit.jupiter.api.Test;

import java.io.*;

/**
 * @author dzy
 * @version 1.0
 */
public class Serialized {
    public static void main(String[] args) {
        String filePath = "E:\\data.dat";
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(filePath);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeInt(100);
            objectOutputStream.writeChar('@');
            objectOutputStream.writeUTF("dzydzy");
            objectOutputStream.writeDouble(100.123);
      //      objectOutputStream.writeObject(new Dog("韩奕辰" , 30));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                objectOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("成功");

    }
}

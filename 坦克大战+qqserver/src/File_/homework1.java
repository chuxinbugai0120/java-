package File_;

import java.io.*;
import java.util.Properties;

/**
 * @author dzy
 * @version 1.0
 */
public class homework1 {

    public static void main(String[] args) throws IOException {
        File file = new File("e:\\sd");
        FileWriter fileWriter = new FileWriter("D:\\javawork\\idea\\project\\房屋出租设计\\src\\File_\\dog.properties");
        Properties properties = new Properties();
        properties.setProperty("name" , "tom");
        properties.setProperty("age" , "5");
        properties.setProperty("color" , "red");
        properties.store(fileWriter , null);
        FileReader fileReader = new FileReader("D:\\javawork\\idea\\project\\房屋出租设计\\src\\File_\\dog.properties");
        properties.load(fileReader);
        properties.list(System.out);
        Dog dog = new Dog();
        dog.name = properties.getProperty("name");
        dog.age = Integer.parseInt(properties.getProperty("age"));
        dog.color = properties.getProperty("color");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("D:\\javawork\\idea\\project\\房屋出租设计\\src\\File_\\dog.dat"));

        System.out.println(dog);
        objectOutputStream.writeUTF(dog.name);
        objectOutputStream.writeInt(dog.age);
        objectOutputStream.writeUTF(dog.color);
        objectOutputStream.writeObject(dog);
        objectOutputStream.close();
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("D:\\javawork\\idea\\project\\房屋出租设计\\src\\File_\\dog.dat"));
        System.out.println(objectInputStream.readUTF());
        System.out.println(objectInputStream.readInt());
        System.out.println(objectInputStream.readUTF());
        try {
            Dog d = (Dog)objectInputStream.readObject();
            System.out.println(d);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        objectInputStream.close();
    }
}

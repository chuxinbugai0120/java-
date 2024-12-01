//package File_;
//
//import java.io.*;
//
///**
// * @author dzy
// * @version 1.0
// */
//public class subSerilized {
//    public static void main(String[] args) {
//        String filePath = "E:\\data.dat";
//        FileInputStream fileInputStream = null;
//        ObjectInputStream objectInputStream = null;
//
//        try {
//            objectInputStream = new ObjectInputStream(new FileInputStream(filePath));
//            System.out.println(objectInputStream.readInt());
//            System.out.println(objectInputStream.readChar());
//            System.out.println(objectInputStream.readUTF());
//            Double dd = objectInputStream.readDouble();
//            System.out.println(dd);
//
//
//
//            try {
//                Dog dog = (Dog)objectInputStream.readObject();
//                System.out.println(dog.getName());
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                objectInputStream.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        System.out.println("成功");
//
//
//    }
//}

package don;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

interface Animal {
    void eat();
}

// 定义第二个接口，继承第一个接口
interface Dog extends Animal {
    void bark();

}

// 实现继承后的接口
class Bulldog implements Dog {

    @Override
    public void eat() {
        System.out.println("Bulldog is eating.");
    }
    @Override
    public void bark() {
        System.out.println("Bulldog is barking.");
    }
}
class Parent {
    public int nn = 80;
    static class Inner{
        private void say()
        {
            System.out.println("@");
        }
    }

    public Inner f()
    {
        return new Inner();
    }

    public static void main(String[] args) throws Exception {

        try {
            f1();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static double cal(int n1, int n2) {
        return n1 / n2;
    }
    public static void f1()
    {
        if(5 > 3)
            throw new RuntimeException("666");
        System.out.println("@@@");
    }
}

class Child extends Parent {

}
class xiaoming extends Child{

}

class Person {
    private String name = "jack";

    public String getName() {
        return name;
    }
}


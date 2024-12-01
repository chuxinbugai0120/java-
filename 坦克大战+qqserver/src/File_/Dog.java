package File_;

import java.io.Serializable;

/**
 * @author dzy
 * @version 1.0
 */
public class Dog implements Serializable {
    public String name;
    public int age;
    public String color;

    public Dog(String name, int age , String color) {
        this.name = name;
        this.age = age;
        this.color = color;
    }
    public Dog() {

    }
    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}' + " color = " + color;
    }
}

package juit;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @author dzy
 * @version 1.0
 */
public class dzydzy {

    public static void main(String[] args) {
        Map<Object,Object> objects = new HashMap<>();
        objects.put("2",76);
        objects.remove("2");

//        Map<String, Integer> objectObjectHashMap = new HashMap<>();
//        objectObjectHashMap.put("jack" , 650);
//        objectObjectHashMap.put("tom" , 1200);
//        objectObjectHashMap.put("smith" , 2900);
//        System.out.println(objectObjectHashMap);
//        objectObjectHashMap.put("jack" , 2600);
//        Set<Map.Entry<String, Integer>> entries = objectObjectHashMap.entrySet();
//        for (Map.Entry<String , Integer> p: entries)
//        {
//            p.setValue(p.getValue() + 100);
//        }
//        Set<String> strings = objectObjectHashMap.keySet();
//        Collection<Integer> values = objectObjectHashMap.values();
//        Iterator<String> iterator = strings.iterator();
//        while (iterator.hasNext()) {
//            Object next =  iterator.next();
//            System.out.println(next);
//        }
//        for(Integer now : values)
//        {
//            System.out.println(now);
//        }
    }

}

class person{
    int name;
    String h;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        person person = (person) o;
        return name == person.name &&
                Objects.equals(h, person.h);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, h);
    }
}

package java2lesson3;

import java.util.*;

public class PhoneBook {

    private Map<String, Set<String>> map = new HashMap<>();

    public static void main(String[] args) {

    }

    public void add(String name, String phoneNumber) {
        if (map.containsKey(name)) {
            map.get(name).add(phoneNumber);

        } else {
            Set<String> set = new HashSet<>();
            set.add(phoneNumber);
            map.put(name, set);


        }
        //// 2 вариант (упрощенный)
/*
        Set<String > set = map.getOrDefault(name, new HashSet<>());
        set.add(phoneNumber);
        map.put(name, set); */

    }
// null возвращать из такого метода нельзя, мы вернем просто пустой список. Так делать круто
    public Set<String> getPhones (String name){
        return map.getOrDefault(name, Collections.emptySet());

    }
}

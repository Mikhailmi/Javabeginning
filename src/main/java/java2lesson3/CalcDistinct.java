package java2lesson3;

import java.util.HashMap;
import java.util.Map;

public class CalcDistinct {


    public static void main(String[] args) {
        String [] strings = new String[] {"a", "b", "c", "d", "e", "a", "c", "g", "e", "o", "p", "m"};
        new CalcDistinct().calcDistinctWords(strings);
    }
    public void calcDistinctWords (String[] array) {
        // key - word, value - count;
        Map<String, Integer> map = new HashMap<>();
        for (String s : array) {
            map.put(s, map.getOrDefault(s, 0) + 1);
        }

        System.out.println(map);
        System.out.println(map.keySet());

    }

}

package java3lesson1;

import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Task2 {

    public static void main(String[] args) {

        String[] strings = {"1", "2", "3"};

        for (int i = 0; i <3; i++) {
            System.out.print(strings[i] + ", ");
        }

        System.out.println();

        List<String> listOfStrings = new ArrayList<String>();

        listOfStrings = Arrays.asList(strings);
        System.out.println(listOfStrings);

    }
}

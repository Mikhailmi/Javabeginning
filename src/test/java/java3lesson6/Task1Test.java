package java3lesson6;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


public class Task1Test {

    private Task1 task1;

    @Test
    public void testAdd1() {
        task1 = new Task1();
        ArrayList<Integer> array1 = new ArrayList();
        array1.add(1);
        array1.add(7);
        int arr1[] = {1, 2, 4, 4, 2, 3, 4, 1, 7};
        Assertions.assertEquals(array1.toString(), task1.SetArray(arr1).toString());

    }
    @Test
    public void testAdd2() {
        task1 = new Task1();
        ArrayList<Integer> array2 = new ArrayList();
        array2.add(1);
        int arr2[] = {3, 5, 4, 3, 2, 0, 4, 4, 1};
        Assertions.assertEquals(array2.toString(), task1.SetArray(arr2).toString());

    }
    @Test
    public void testAdd3() {
        task1 = new Task1();
        ArrayList<Integer> array3 = new ArrayList();
        int arr3[] = {1, 2, 3, 5, 2, 3, 8, 1, 7};
        Assertions.assertEquals(array3.toString(), task1.SetArray(arr3).toString());

    }
    @Test
    public void testAdd4() {
        task1 = new Task1();
        ArrayList<Integer> array4 = new ArrayList();
        array4.add(7);
        int arr4[] = {4, 4, 4, 4, 4, 4, 4, 4, 7};
        Assertions.assertEquals(array4.toString(), task1.SetArray(arr4).toString());

    }


}

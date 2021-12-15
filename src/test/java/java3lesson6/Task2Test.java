package java3lesson6;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class Task2Test {

    private Task2 task2;

    @Test
    public void testAdd1() {
        task2 = new Task2();
        int arr1[] = {1, 1, 1, 4, 4, 1, 4, 4};
        Assertions.assertEquals(true, task2.SetArray(arr1));

    }

    public void testAdd2() {
        task2 = new Task2();
        int arr2[] = {1, 1, 1, 1, 1, 1};
        Assertions.assertEquals(false, task2.SetArray(arr2));

    }

    public void testAdd3() {
        task2 = new Task2();
        int arr3[] = {4, 4, 4, 4};
        Assertions.assertEquals(false, task2.SetArray(arr3));

    }

    public void testAdd4() {
        task2 = new Task2();
        int arr4[] = {1, 4, 4, 1, 1, 4, 3};
        Assertions.assertEquals(false, task2.SetArray(arr4));

    }



}

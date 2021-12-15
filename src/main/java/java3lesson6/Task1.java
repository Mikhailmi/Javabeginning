package java3lesson6;

import java.util.ArrayList;

public class Task1 {

    public static void main(String[] args) {
        int arr1[] = {1, 2, 4, 4, 2, 3, 4, 1, 7};
        SetArray(arr1);
        int arr2[] = {3, 5, 4, 3, 2, 0, 4, 4, 1};
        SetArray(arr2);
        int arr3[] = {1, 2, 3, 5, 2, 3, 8, 1, 7};
        SetArray(arr3);
        int arr4[] = {4, 4, 4, 4, 4, 4, 4, 4, 7};
        SetArray(arr4);
    }

    public static ArrayList<Integer> SetArray(int [] arr) {
        ArrayList<Integer> array = new ArrayList(); // 1. Объявили новый массив
        int countFours = 0; // 2. Счетчик четверок в массиве
        try {

            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == 4) {
                    countFours++;
                }
            }
            if (countFours == 0) {
                throw new RuntimeException(); // 3. Бросаем исключение, если в исходном массиве нет четверки
            }
            int count = 0; // 4. Счетчик количества элементов массива после заключительного элемента 4
            for (int i = arr.length; i > 0; i--) {
                if (arr[i - 1] != 4) {
                    count++; //5. Посчитали количество элементов массива после заключительного элемента 4
                } else {
                    break;
                }
            }

            for (int i = 0; i < count; i++) {
                array.add(arr[arr.length - count + i]); // 6. Записали новый массив

                System.out.print(array.get(i));
            }
            System.out.println();

        } catch (RuntimeException e) {
            e.printStackTrace();
        }

        return array;
    }
}

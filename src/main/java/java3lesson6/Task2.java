package java3lesson6;

public class Task2 {

    public static void main(String[] args) {
        int arr1[] = {1, 1, 1, 4, 4, 1, 4, 4};
        System.out.println(SetArray(arr1));
        int arr2[] = {1, 1, 1, 1, 1, 1};
        System.out.println(SetArray(arr2));
        int arr3[] = {4, 4, 4, 4};
        System.out.println(SetArray(arr3));
        int arr4[] = {1, 4, 4, 1, 1, 4, 3};
        System.out.println(SetArray(arr4));
    }

    public static boolean SetArray(int [] array) {
        int countOne = 0;
        int countFour = 0;
        int countOther = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == 1) {
                countOne++;
            } else if (array[i] == 4) {
                countFour++;
            } else {
                countOther++;
            }
        }
        if (countOne !=0 && countFour != 0 && countOther == 0) {
            return true;
        }
        return false;
    }

}

import java.util.Arrays;

public class HomeWorkApp3 {

    public static void main(String[] args) {
        array0and1();
        array0to100();
        arrayDoubleAllLess6();
        arrayDiagonal1(2);
        arrayIntLength(7, 11);
        arrayMinAndMax();
        arrayTrueIfSumLeftEqualsRight();
        arrayMoveOnInt();

    }

    public static void array0and1() {
        int[] arr = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1 - arr[i];
        }
        System.out.println(Arrays.toString(arr));

    }

    public static void array0to100() {
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
        System.out.println(Arrays.toString(arr));
    }

    public static void arrayDoubleAllLess6() {
        int[] arr = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 6) {
                arr[i] = 2 * arr[i];
            }
        }
        System.out.println(Arrays.toString(arr));
    }

    public static void arrayDiagonal1(int a) {

    // a - это размерность массива

        int counter = 1;
        int[][] table = new int[a][a];
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < a; j++) {
                if (i == j){
                table[i][j] = counter;
                 }else if (i == a - j - 1) {
                    table [i][j] = counter;
                }
                else table[i][j] = 0;

                System.out.print(table[i][j] + " ");
            }

            System.out.println();

        }

    }


    public static void arrayIntLength(int len, int initialValue){

        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = initialValue;
        }
        System.out.println(Arrays.toString(arr));

    }
    public static void arrayMinAndMax(){

        int[] arr = {1, 2, 0, -50, 8, -10, 15, -20, 4, 5};
        int max = arr[0];
        int min = arr [0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
            if (arr[i] < min) {
                min = arr[i];
            }

        }
        System.out.println(max);
        System.out.println(min);
    }

    public static void arrayTrueIfSumLeftEqualsRight(){
        int[] arr = {12, 3, 0, 1, 1, 1, 4, 3, 5, 2};
        boolean a = false;
        int sumLeft = 0;
        int sumRight = 0;
        for (int i = 0; i < arr.length; i++){
            sumLeft += arr[i];
        }
        for (int b = 0; b < arr.length; b++){
            sumLeft = sumLeft - arr[arr.length - 1 - b];
            sumRight += arr[arr.length - 1 - b];
            if (sumLeft == sumRight) {
                a = true;
            }
        }

        System.out.println(a);

    }
    public static void arrayMoveOnInt(){
        int[] arr = {2, 4, 6, 8, 10, 12, 14, 16, 18, 20};
        int a = -2;
        int b = a % arr.length;
        if (b == 0) {
            System.out.println(Arrays.toString(arr));
        } else if (b > 0) {

            for (int i = 0; i < b; i++) {
                System.out.print(arr[arr.length-b+i] + " ");
            } for (int i = 0; i < arr.length-b; i++) {
                System.out.print(arr[i] + " ");
            }

            } else if (b<0) {
            for (int i = 0; i < arr.length + b; i++){
                System.out.print(arr[i - b] + " ");
            } for (int i = 0; i < b * (-1); i++) {
                System.out.print(arr[i] + " ");

            }        }

            }





    }

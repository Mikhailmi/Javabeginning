package java3lesson1;



public class Task1 {

    public static void main(String[] args) {
        setArray();
    }

    public static void setArray() {

        int[] a = {1, 2, 3};

        for (int i = 0; i < 3; i++) {
            System.out.print(a[i] + " ");

        }

        System.out.println();

        int b = a[0];

        a[0] = a[1];

        a[1] = b;

        for (int i = 0; i < 3; i++) {
            System.out.print(a[i] + " ");
        }


    }

}
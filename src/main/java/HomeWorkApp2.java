import java.sql.SQLOutput;

public class HomeWorkApp2 {

    public static void main(String[] args) {
        compare(10,3);
        number(9);
        lies(4);
        writing(4, "Hi");
        year(3);
    }

    public static void compare (int a, int b) {

        boolean c = false;
        int d = a + b;
        if (d<10) {
            System.out.println(c);
        }else if (d>20){
            System.out.println(c);
        }else {
            c = true;
            System.out.println(c);

        }

    }

    public static void number(int a){

                if (a<0){
            System.out.println("Число отрицательное");
        }else {
            System.out.println("Число положительное");
        }

    }

    public static void lies(int a){

        boolean b;
        if (a<0) {
            b = true;
            System.out.println(b);
        }else {
            b = false;
            System.out.println(b);
        }
    }
    public static void writing(int a, String b){

        for (int c = 0; c < a; c++) {
            System.out.println(b);

        }

    }

    public static void year(int a){

        int b = a%4;
        int c = a%100;
        int d = a%400;
        boolean e;
        if (d == 0) {
            e = true;
            System.out.println(e);
        }else if (c == 0){
            e = false;
            System.out.println(e);
        }else if (b == 0) {
            e = true;
            System.out.println(e);
        }else {
            e = false;
            System.out.println(e);
        }


    }




}

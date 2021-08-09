import java.util.Random;
import java.util.Scanner;

public class HomeWorkApp4_block_3x3 {
    public static int SIZE = 3;
    public static int DOTS_TO_WIN = 3;
    public static int COUNTER = 0;
    public static int q, w;
    public static final char DOT_EMPTY = '•';
    public static final char DOT_X = 'X';
    public static final char DOT_O = 'O';
    public static char[][] map;
    public static Scanner sc = new Scanner(System.in);
    public static Random rand = new Random();

    public static void main(String[] args) {
        initMap();
        printMap();

        while (true) {
            humanTurn();
            printMap();
            if (checkWinCycle(DOT_X)) {
                System.out.println("Победил человек");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
            aiTurn();
            printMap();
            if (checkWinCycle(DOT_O)) {
                System.out.println("Победил Искуственный Интеллект");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
        }
        System.out.println("Игра закончена");


    }

    public static void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    public static void printMap() {
        for (int i = 0; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + " ");

            }
            System.out.println();
        }
        System.out.println();
    }

    public static void humanTurn() {
        int x, y;
        do {
            System.out.println("Введите координаты в формате X Y");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
            COUNTER++;
        } while (!isCellValid(x, y));
        map[y][x] = DOT_X;
    }

    private static boolean isCellValid(int x, int y) {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) return false;
        if (map[y][x] == DOT_EMPTY) return true;
        return false;
    }

    public static void aiTurn() {
        int x = 1, y = 2;
        do { switch (COUNTER) {

            case 1: { /* после первого хода человека. Отталкиваемся от того, что лучший ход - по центру
                 если человек сходил по центру, то лучший ход - в один из углов*/
                if (map[1][1] == DOT_X) {
                    q = rand.nextInt(2);
                    if (q == 1) x = 0;
                    else x = 2;

                    w = rand.nextInt(2);
                    if (w == 1) y = 0;
                    else y = 2;
                } else {
                    x = 1;
                    y = 1;
                }
            }
            case 2: {// После второго хода человека
                   if (map[1][1] == DOT_X) { // если первый ход человека по центру
                       for (int j =0; j < 3; j += 2) {
                           for (int i = 0; i < 3; i++) { // блокируем крестики в первой и третьей строке
                               if (map[j][i] == DOT_X) {
                                   x = 2 - i;
                                   y = 2 - j;
                               }
                           } if (map[1][j] == DOT_X) {x = 2 - j; y = 1; } // блокируем крестики во второй строке
                       }
                }
                   if (map [1][1] == DOT_O) { // если первых ход человека не по центру
                       for (int j = 0; j < 3; j +=2){ // номер строки
                       for (int i = 0; i < 3; i += 2) { // номер столбца
                           if (map[j][i] == DOT_X && map[j][1] == DOT_X ) { // блокируем строки, в которых два Х рядом
                               x = 2 - i;
                               y = j;
                           } if (map[j][i] == DOT_X && map [1][i] == DOT_X) { // блокируем стоблцы, в которых два Х рядом
                               x = i;
                               y = 2 - j;
                           } if (map[0][i] == DOT_X && map [2][i] == DOT_X) { // блокируем в столбцах, где Х с промежутком
                               x = i;
                               y = 1;
                           } if (map[j][0] == DOT_X && map [j][2] == DOT_X){ // блокируем в строках, где Х с промежутком
                               x = 1;
                               y = j;
                           }
                           }
                       }
                }
            }
        }
        } while (!isCellValid(x, y));
        System.out.println("Компьютер походил в точку " + (x + 1) + " " + (y + 1));
        map[y][x] = DOT_O;
    }


    public static boolean checkWinCycle(char symb) {
        for (int i = 0; i < 3; i++) {
            if (map[i][0] == symb && map[i][1] == symb && map[i][2] == symb) return true;
        }
        for (int i = 0; i < 3; i++) {
            if (map[0][i] == symb && map[1][i] == symb && map[2][i] == symb) return true;
        }
        if (map[0][0] == symb && map[1][1] == symb && map[2][2] == symb) return true;
        if (map[2][0] == symb && map[1][1] == symb && map[0][2] == symb) return true;

        return false;
    }

    public static boolean isMapFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) return false;
            }
        }
        return true;

    }

}
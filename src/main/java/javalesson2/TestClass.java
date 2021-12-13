package javalesson2;

public class TestClass {

    public static void main(String args[]) {
        int row;
        int column;
        row = 4;
        column = 4;
       String[][] newArray = new String[row][column];
        for (int i = 0; i < newArray.length; i++) {
            for (int j = 0; j < newArray.length; j++) {
                newArray[i][j] = "3";
            }
        }
              try {
           createArr(newArray, row, column);
        } catch (MyArraySizeException e) {
           e.printStackTrace();
        } catch (MyArrayDataException e) {
            e.printStackTrace();
        }
    }

    public static int createArr(String[][] array, int row, int column) {
        if (row != 4 || column != 4) throw new MyArraySizeException(row, column);
        System.out.println("Массив создан");
        int sum = 0;
        int[][] newArrInt = new int[row][column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                try {
                    newArrInt[i][j] = Integer.parseInt(array[i][j]);
                    sum += newArrInt[i][j];
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(i, j);
                }
            }
        }
       System.out.println("Сумма элементов двумерного массива размером 4х4 составляет: " + sum);
       return sum;
    }
}

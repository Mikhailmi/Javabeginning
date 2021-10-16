package javalesson2;

public class MyArraySizeException extends RuntimeException {


    public MyArraySizeException(int row, int column) {
        System.out.println("Неправильная размерность. Массив должен быть размеров 4х4. У Вас " + row + " строки и " + column + " столбца");
    }
}



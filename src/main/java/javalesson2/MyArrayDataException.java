package javalesson2;

public class MyArrayDataException extends RuntimeException{

    int i, j;

    public MyArrayDataException(int i, int j) {
        super("Неправильная запись в " + i + " строке " + j + " столбце.");
        this.i = i;
        this.j = j;

    }

}

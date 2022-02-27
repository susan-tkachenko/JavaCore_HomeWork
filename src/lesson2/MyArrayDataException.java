package lesson2;

public class MyArrayDataException extends Exception {

    public MyArrayDataException(int rowIndex, int columnIndex, Throwable throwable) {
        super("Некорректные данные в " + (rowIndex + 1) + " строке " + (columnIndex + 1) + " столбце", throwable);
    }
}

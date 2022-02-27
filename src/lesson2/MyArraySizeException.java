package lesson2;

public class MyArraySizeException extends Exception {

    public MyArraySizeException(int expectedRowsCount, int actualRowsCount) {
        super("Неверное количество строк. " +
                "Ожидается " + expectedRowsCount + ", но фактически " + actualRowsCount + ".");
    }

    public MyArraySizeException(int rowIndex, int expectedColumnsCount, int actualColumnsCount) {
        super("Неверное количество столбцов в " + (rowIndex + 1) + " строке. " +
                "Ожидается " + expectedColumnsCount + ", но фактически " + actualColumnsCount + ".");
    }
}

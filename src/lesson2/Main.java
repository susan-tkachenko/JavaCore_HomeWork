package lesson2;

public class Main {

    public static int sum(String[][] arrays) throws MyArraySizeException, MyArrayDataException {
        if (arrays.length != 4) {
            throw new MyArraySizeException(4, arrays.length);
        }

        int resultSum = 0;
        for (int i = 0; i < arrays.length; i++) {
            String[] subArray = arrays[i];

            if (subArray.length != 4) {
                throw new MyArraySizeException(i, 4, subArray.length);
            }

            for (int j = 0; j < subArray.length; j++) {
                String numberString = subArray[j];

                int parsedNumber;
                try {
                    parsedNumber = Integer.parseInt(numberString);
                } catch (NumberFormatException ex) {
                    throw new MyArrayDataException(i, j, ex);
                }

                resultSum = resultSum + parsedNumber;
            }
        }

        return resultSum;
    }

    public static void main(String[] args) {
        String[] a1 = {"1", "0", "1", "1"};
        String[] a2 = {"21", "343", "-11", "-2"};
        String[] a3 = {"31", "433", "11", "211"};
        String[] a4 = {"55", "3773", "181", "22"};
        String[][] array = new String[][]{a1, a2, a3, a4};

        try {
            int sumOfArray = sum(array);
            System.out.println("Сумма = " + sumOfArray);
        } catch (MyArraySizeException | MyArrayDataException ex) {
            ex.printStackTrace();
        }
    }
}

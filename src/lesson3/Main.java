package lesson3;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Integer[] arr = {1, 2, 3, 4, 5};
        System.out.println("Before swap = " + Arrays.toString(arr));
        swap(arr, 2, 4);
        System.out.println("After swap = " + Arrays.toString(arr));

        Box<Orange> oranges = new Box<Orange>(Orange.WEIGHT)
                .add(new Orange())
                .add(new Orange());

        Box<Apple> apples1 = new Box<Apple>(Apple.WEIGHT)
                .add(new Apple())
                .add(new Apple())
                .add(new Apple());

        Box<Apple> apples2 = new Box<Apple>(Apple.WEIGHT)
                .add(new Apple());

        System.out.println(oranges.compare(apples1));
        System.out.println(oranges.compare(apples2));

        apples1.transferTo(apples2);

        System.out.println(oranges.compare(apples1));
        System.out.println(oranges.compare(apples2));
    }

    public static <T> void swap(T[] array, int a, int b) {
        if (a < 0 || array.length <= a || b < 0 || array.length <= b || array.length < 2 || a == b) {
            System.out.println("Некорректные индексы");
            return;
        }

        T tmp = array[a];
        array[a] = array[b];
        array[b] = tmp;
    }

}

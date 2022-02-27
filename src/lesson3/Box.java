package lesson3;

import java.util.ArrayList;
import java.util.List;

public class Box<T extends Fruit> {

    private final float oneFruitWeight;
    private final List<T> fruits = new ArrayList<>();

    public Box(float oneFruitWeight) {
        this.oneFruitWeight = oneFruitWeight;
    }

    public Box<T> add(T fruit) {
        fruits.add(fruit);
        return this;
    }

    public float getWeight() {
        return oneFruitWeight * fruits.size();
    }

    public boolean compare(Box<?> other) {
        return this.getWeight() == other.getWeight();
    }

    public void transferTo(Box<T> other) {
        for (T fruit : fruits) {
            other.add(fruit);
        }
        fruits.clear();
    }

}

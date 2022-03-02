package lesson5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class AppData {

    private final Random random = new Random();

    private final String[] headers;
    private final List<List<Integer>> table = new ArrayList<>();

    public AppData(String[] headers) {
        this(headers, new ArrayList<>());
    }

    public AppData(String[] headers, List<List<Integer>> table) {
        this.headers = headers;
        for (List<Integer> row : table) {
            addRow(row);
        }
    }

    public String[] getHeaders() {
        return headers;
    }

    public List<List<Integer>> getTable() {
        return table;
    }

    public AppData addRow(List<Integer> row) {
        if (row.size() != headers.length) {
            throw new IllegalArgumentException("Invalid columns size");
        }

        table.add(row);

        return this;
    }

    public AppData addRandomRows(int count) {
        while (count-- > 0) {
            addRandomRow();
        }

        return this;
    }

    public AppData addRandomRow() {
        List<Integer> row = new ArrayList<>();

        for (int i = 0; i < headers.length; i++) {
            row.add(random.nextInt(100));
        }

        return addRow(row);
    }

    @Override
    public String toString() {
        return "AppData{" +
                "headers=" + Arrays.toString(headers) +
                ", table=" + table +
                '}';
    }

}

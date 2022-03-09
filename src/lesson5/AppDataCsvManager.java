package lesson5;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

public class AppDataCsvManager {

    public static final Charset CHARSET = UTF_8;
    public static final String DELIMITER = ";";

    public static void saveTo(String fileName, AppData appData) {
        try (FileOutputStream fos = new FileOutputStream(fileName)) {
            String csvData = asCsv(appData);
            fos.write(csvData.getBytes(CHARSET));
        } catch (Exception ex) {
            throw new RuntimeException("Unexpected exception", ex);
        }
    }

    public static AppData loadFrom(String fileName) {
        try (FileInputStream fis = new FileInputStream(fileName)) {
            String csvData = new String(fis.readAllBytes(), UTF_8);
            String[] rows = csvData.split("\n");

            String[] headers = rows[0].split(DELIMITER);
            List<List<Integer>> table = new ArrayList<>();

            for (int i = 1; i < rows.length; i++) {
                table.add(parseRow(rows[i]));
            }

            return new AppData(headers, table);
        } catch (Exception ex) {
            throw new RuntimeException("Unexpected exception", ex);
        }
    }

    private static String asCsv(AppData appData) {
        String result = "";

        String[] headers = appData.getHeaders();
        for (int i = 0; i < headers.length; i++) {
            if (i > 0) {
                result += DELIMITER;
            }
            result += headers[i];
        }

        for (List<Integer> row : appData.getTable()) {
            result += "\n";

            for (int i = 0; i < row.size(); i++) {
                if (i > 0) {
                    result += DELIMITER;
                }
                result += row.get(i);
            }
        }

        return result;
    }

    private static List<Integer> parseRow(String row) {
        List<Integer> result = new ArrayList<>();

        for (String column : row.split(DELIMITER)) {
            result.add(Integer.parseInt(column));
        }

        return result;
    }

}

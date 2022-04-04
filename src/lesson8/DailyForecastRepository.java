package lesson8;

import lesson7.DailyForecast;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class DailyForecastRepository {

    private static final String DB_URL = "jdbc:sqlite:homework8.db";

    private static final String TABLE_NAME = "daily_forecast";

    private static final String CREATE_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
            "id integer PRIMARY KEY, " +
            "city text NOT NULL, " +
            "timestamp integer NOT NULL, " +
            "dayText text NOT NULL, " +
            "nightText text NOT NULL, " +
            "minTemperature double NOT NULL, " +
            "maxTemperature double NOT NULL" +
            ")";

    private static final String INSERT_QUERY = "INSERT INTO " + TABLE_NAME + "(" +
            "city, " +
            "timestamp, " +
            "dayText, " +
            "nightText, " +
            "minTemperature, " +
            "maxTemperature" +
            ") " +
            "VALUES(?, ?, ?, ?, ?, ?)";

    private static String SELECT_ALL_QUERY = "SELECT * FROM " + TABLE_NAME;

    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException("Не найден JDBC драйвер для sqlite");
        }

        try (Connection connection = DriverManager.getConnection(DB_URL);
             Statement statement = connection.createStatement()) {
            statement.execute(CREATE_TABLE_QUERY);
        } catch (SQLException ex) {
            throw new RuntimeException("Ошибка при создании таблицы", ex);
        }
    }

    public static void persist(List<DailyForecast> forecasts) {
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            for (DailyForecast forecast : forecasts) {
                preparedStatement.setString(1, forecast.getCity());
                preparedStatement.setLong(2, forecast.getDate().toEpochMilli());
                preparedStatement.setString(3, forecast.getDayTextDescription());
                preparedStatement.setString(4, forecast.getNightTextDescription());
                preparedStatement.setDouble(5, forecast.getMinimumTemperature());
                preparedStatement.setDouble(6, forecast.getMaximumTemperature());
                preparedStatement.execute();
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Ошибка при сохранении", ex);
        }
    }

    public static List<DailyForecast> getAll() {
        List<DailyForecast> forecasts = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DB_URL);
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_QUERY);

            while (resultSet.next()) {
                forecasts.add(new DailyForecast(
                        resultSet.getString("city"),
                        Instant.ofEpochMilli(resultSet.getLong("timestamp")),
                        resultSet.getDouble("minTemperature"),
                        resultSet.getDouble("maxTemperature"),
                        resultSet.getString("dayText"),
                        resultSet.getString("nightText")
                ));
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Ошибка при чтении из таблицы", ex);
        }

        return forecasts;
    }

}

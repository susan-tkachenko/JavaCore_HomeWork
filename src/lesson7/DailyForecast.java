package lesson7;

import javax.json.JsonArray;
import javax.json.JsonObject;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class DailyForecast {

    private final String city;
    private final Instant date;
    private final double minimumTemperature;
    private final double maximumTemperature;
    private final String dayTextDescription;
    private final String nightTextDescription;

    public DailyForecast(
            String city,
            Instant date,
            double minimumTemperature,
            double maximumTemperature,
            String dayTextDescription,
            String nightTextDescription) {
        this.city = city;
        this.date = date;
        this.minimumTemperature = minimumTemperature;
        this.maximumTemperature = maximumTemperature;
        this.dayTextDescription = dayTextDescription;
        this.nightTextDescription = nightTextDescription;
    }

    public String getCity() {
        return city;
    }

    public Instant getDate() {
        return date;
    }

    public double getMinimumTemperature() {
        return minimumTemperature;
    }

    public double getMaximumTemperature() {
        return maximumTemperature;
    }

    public String getDayTextDescription() {
        return dayTextDescription;
    }

    public String getNightTextDescription() {
        return nightTextDescription;
    }

    public static List<DailyForecast> parse(String city, JsonObject json) {
        List<DailyForecast> dailyForecasts = new ArrayList<>();

        JsonArray dailyForecastsJson = json.getJsonArray("DailyForecasts");
        for (int i = 0; i < dailyForecastsJson.size(); i++) {
            JsonObject jsonForecast = dailyForecastsJson.getJsonObject(i);
            dailyForecasts.add(parseDailyForecast(city, jsonForecast));
        }

        return dailyForecasts;
    }

    private static DailyForecast parseDailyForecast(String city, JsonObject json) {
        Instant date = Instant.ofEpochSecond(json.getInt("EpochDate"));

        JsonObject temperatureJson = json.getJsonObject("Temperature");

        double minimumTemperature = temperatureJson.getJsonObject("Minimum").getJsonNumber("Value").doubleValue();
        double maximumTemperature = temperatureJson.getJsonObject("Maximum").getJsonNumber("Value").doubleValue();
        String dayTextDescription = json.getJsonObject("Day").getString("IconPhrase");
        String nightTextDescription = json.getJsonObject("Night").getString("IconPhrase");

        return new DailyForecast(
                city,
                date,
                minimumTemperature,
                maximumTemperature,
                dayTextDescription,
                nightTextDescription
        );
    }

}

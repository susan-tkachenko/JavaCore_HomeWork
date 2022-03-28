package lesson7;

import javax.json.JsonArray;
import javax.json.JsonObject;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class WeatherResponse {

    private final Instant date;
    private final double minimumTemperature;
    private final double maximumTemperature;
    private final String dayTextDescription;
    private final String nightTextDescription;

    public WeatherResponse(
            Instant date,
            double minimumTemperature,
            double maximumTemperature,
            String dayTextDescription,
            String nightTextDescription) {
        this.date = date;
        this.minimumTemperature = minimumTemperature;
        this.maximumTemperature = maximumTemperature;
        this.dayTextDescription = dayTextDescription;
        this.nightTextDescription = nightTextDescription;
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

    public static List<WeatherResponse> parse(JsonObject json) {
        List<WeatherResponse> dailyForecasts = new ArrayList<>();

        JsonArray dailyForecastsJson = json.getJsonArray("DailyForecasts");
        for (int i = 0; i < dailyForecastsJson.size(); i++) {
            JsonObject jsonForecast = dailyForecastsJson.getJsonObject(i);
            dailyForecasts.add(parseDailyForecast(jsonForecast));
        }

        return dailyForecasts;
    }

    private static WeatherResponse parseDailyForecast(JsonObject json) {
        Instant date = Instant.ofEpochSecond(json.getInt("EpochDate"));

        JsonObject temperatureJson = json.getJsonObject("Temperature");

        double minimumTemperature = temperatureJson.getJsonObject("Minimum").getJsonNumber("Value").doubleValue();
        double maximumTemperature = temperatureJson.getJsonObject("Maximum").getJsonNumber("Value").doubleValue();
        String dayTextDescription = json.getJsonObject("Day").getString("IconPhrase");
        String nightTextDescription = json.getJsonObject("Night").getString("IconPhrase");

        return new WeatherResponse(
                date,
                minimumTemperature,
                maximumTemperature,
                dayTextDescription,
                nightTextDescription
        );
    }

}

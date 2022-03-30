package lesson7;

import javax.json.Json;
import javax.json.JsonReader;
import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.util.List;

public class DailyForecastService {

    private static final String API_KEY = "UexDbt05hE0D3dSWKGDrkI0m0g7OcddI";
    private static final String API_KEY_QUERY_PARAM = "apikey=" + API_KEY;
    private static final String SITE = "http://dataservice.accuweather.com";

    public static List<DailyForecast> getFiveDayForecasts(String city) {
        try {
            return getFiveDayForecastsJson(city);
        } catch (RuntimeException ex) {
            throw new RuntimeException("Не удалось прочитать данные с сервера", ex);
        }
    }

    private static String getLocationKey(String city) {
        String response = getResponse("%s/locations/v1/search?q=%s&%s".formatted(SITE, city, API_KEY_QUERY_PARAM));
        String locationKeyStartTemplate = "\"Key\":";
        int from = response.indexOf(locationKeyStartTemplate) + locationKeyStartTemplate.length() + 1;
        int to = response.indexOf("\"", from);
        return response.substring(from, to);
    }

    private static List<DailyForecast> getFiveDayForecastsJson(String city) {
        String locationKey = getLocationKey(city);
        String responseJson = getResponse("%s/forecasts/v1/daily/5day/%s?metric=true&%s".formatted(SITE, locationKey, API_KEY_QUERY_PARAM));
        StringReader responseStringReader = new StringReader(responseJson);
        JsonReader responseJsonReader = Json.createReader(responseStringReader);
        return DailyForecast.parse(city, responseJsonReader.readObject());
    }

    private static String getResponse(String url) {
        try {
            return new String(new URL(url).openConnection().getInputStream().readAllBytes());
        } catch (IOException ex) {
            throw new RuntimeException("Ошибка при обращении к " + url, ex);
        }
    }

}

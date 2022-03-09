package lesson6;

import java.io.IOException;
import java.net.URL;

public class Main {

    private static final String API_KEY = "UexDbt05hE0D3dSWKGDrkI0m0g7OcddI";
    private static final String API_KEY_QUERY_PARAM = "apikey=" + API_KEY;
    private static final String SITE = "http://dataservice.accuweather.com";

    public static void main(String[] args) {
        String locationKey = getLocationKey("saint-petersburg");
        String fiveDayForecastsJson = getFiveDayForecastsJson(locationKey);
        System.out.println(fiveDayForecastsJson);
    }

    private static String getLocationKey(String city) {
        String response = getResponse("%s/locations/v1/search?q=%s&%s".formatted(SITE, city, API_KEY_QUERY_PARAM));
        String locationKeyStartTemplate = "\"Key\":";
        int from = response.indexOf(locationKeyStartTemplate) + locationKeyStartTemplate.length() + 1;
        int to = response.indexOf("\"", from);
        return response.substring(from, to);
    }

    private static String getFiveDayForecastsJson(String locationKey) {
        return getResponse("%s/forecasts/v1/daily/5day/%s?metric=true&%s".formatted(SITE, locationKey, API_KEY_QUERY_PARAM));
    }

    private static String getResponse(String url) {
        try {
            return new String(new URL(url).openConnection().getInputStream().readAllBytes());
        } catch (IOException ex) {
            throw new RuntimeException("Ошибка при обращении к " + url, ex);
        }
    }

}

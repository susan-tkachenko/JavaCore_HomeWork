package lesson7;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<DailyForecast> fiveDayForecasts = DailyForecastService.getFiveDayForecasts("saint-petersburg");

        for (DailyForecast forecast : fiveDayForecasts) {
            System.out.printf(
                    "В городе %s на дату %s ожидается %s, %s, температура - %s и %s%n",
                    forecast.getCity(),
                    forecast.getDate(),
                    forecast.getDayTextDescription(),
                    forecast.getNightTextDescription(),
                    forecast.getMinimumTemperature(),
                    forecast.getMaximumTemperature()
            );
        }
    }

}

package lesson8;

import lesson7.DailyForecast;
import lesson7.DailyForecastService;

public class Main {

    public static void main(String[] args) {
        DailyForecastRepository.persist(DailyForecastService.getFiveDayForecasts("saint-petersburg"));

        for (DailyForecast forecast : DailyForecastRepository.getAll()) {
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

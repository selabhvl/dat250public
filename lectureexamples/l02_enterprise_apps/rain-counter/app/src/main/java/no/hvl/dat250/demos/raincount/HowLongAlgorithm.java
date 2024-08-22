package no.hvl.dat250.demos.raincount;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class HowLongAlgorithm {

    private final WeatherForecastSource forecastSource;

    public HowLongAlgorithm(WeatherForecastSource forecastSource) {
        this.forecastSource = forecastSource;
    }

    public long calculate(OffsetDateTime start) {
        OffsetDateTime resetStart = OffsetDateTime.of(
                start.getYear(),
                start.getMonthValue(),
                start.getDayOfMonth(),
                start.getHour(),
                0,
                0,
                0,
                start.getOffset());
        Iterator<Forecast> forecasts = forecastSource.getForecast().stream()
                .filter(forecast -> !forecast.getTs().isBefore(resetStart))
                .sorted(Comparator.comparing(Forecast::getTs))
                .toList().iterator();
        if (!forecasts.hasNext()) {
            throw new RuntimeException("No data available :(");
        }
        Forecast first = forecasts.next();
        OffsetDateTime current = first.getTs();
        long hours = 0;
        boolean isDry = first.getRain() == 0.0;
        while (forecasts.hasNext()) {
            Forecast next = forecasts.next();
            long diff = ChronoUnit.HOURS.between(current, next.getTs());
            hours += isDry ? -1 * diff : diff;

            if ((isDry && next.getRain() != 0.0) || (!isDry && next.getRain() == 0.0)) {
                return hours;
            }
            current = next.getTs();
        }
        throw new RuntimeException("Not enough data available");
    }

}

package no.hvl.dat250.l02;



import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.Iterator;

public class HowLongAlgorithm {

    public static enum ExceptionType {
        NO_DATA,
        NOT_ENOUGH_DATA,

        TOO_FAR_ADVANCED
    }

    public static class AlgorithmException extends Exception {
        public ExceptionType type;

        public AlgorithmException(ExceptionType type) {
            this.type = type;
        }
    }

    private final WeatherForecastSource forecastSource;

    public HowLongAlgorithm(WeatherForecastSource forecastSource) {
        this.forecastSource = forecastSource;
    }

    public long calculate(OffsetDateTime start) throws AlgorithmException {
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
            throw new AlgorithmException(ExceptionType.NO_DATA);
        }
        Forecast first = forecasts.next();
        OffsetDateTime current = first.getTs();
        long initDiff = ChronoUnit.HOURS.between(start, current);
        if (initDiff > 2) {
            throw new AlgorithmException(ExceptionType.TOO_FAR_ADVANCED);
        }
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
        throw new AlgorithmException(ExceptionType.NOT_ENOUGH_DATA);
    }

}

package no.hvl.dat250.rest.util;



import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

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



    public long calculate(List<Weather.ForecastHour> forecastHours, OffsetDateTime start) throws AlgorithmException {
        OffsetDateTime resetStart = OffsetDateTime.of(
                start.getYear(),
                start.getMonthValue(),
                start.getDayOfMonth(),
                start.getHour(),
                0,
                0,
                0,
                start.getOffset());
        Iterator<Weather.ForecastHour> forecasts = forecastHours.stream()
                .filter(forecast -> !forecast.getHour().isBefore(resetStart.toInstant()))
                .sorted(Comparator.comparing(Weather.ForecastHour::getHour))
                .toList().iterator();
        if (!forecasts.hasNext()) {
            throw new AlgorithmException(ExceptionType.NO_DATA);
        }
        Weather.ForecastHour first = forecasts.next();
        Instant current = first.getHour();
        long initDiff = ChronoUnit.HOURS.between(start, current);
        if (initDiff > 2) {
            throw new AlgorithmException(ExceptionType.TOO_FAR_ADVANCED);
        }
        long hours = 0;
        boolean isDry = first.getPrecipitation() == 0.0;
        while (forecasts.hasNext()) {
            Weather.ForecastHour next = forecasts.next();
            long diff = ChronoUnit.HOURS.between(current, next.getHour());
            hours += isDry ? -1 * diff : diff;

            if ((isDry && next.getPrecipitation() != 0.0) || (!isDry && next.getPrecipitation() == 0.0)) {
                return hours;
            }
            current = next.getHour();
        }
        throw new AlgorithmException(ExceptionType.NOT_ENOUGH_DATA);
    }

}

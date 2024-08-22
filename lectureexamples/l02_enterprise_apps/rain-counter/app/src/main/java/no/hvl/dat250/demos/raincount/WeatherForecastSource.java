package no.hvl.dat250.demos.raincount;

import java.util.Collection;

public interface WeatherForecastSource {

    Collection<Forecast> getForecast();

}

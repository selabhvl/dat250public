package no.hvl.dat250.demos.raincount;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Collection;
import static org.junit.jupiter.api.Assertions.*;

public class ParsingTest {

    @Test
    public void testParseBigFile() {
        File input = new File("src/test/resources/forecast1.json");
        WeatherForecastSource source = new JsonFileBasedForecastSource(input);
        Collection<Forecast> forecast = source.getForecast();
        assertNotNull(forecast);
        assertNotEquals(forecast.size(), 0); // should not be empty
    }
}

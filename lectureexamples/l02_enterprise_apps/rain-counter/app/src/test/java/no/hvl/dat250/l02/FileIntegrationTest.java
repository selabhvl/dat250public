package no.hvl.dat250.l02;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import static org.junit.jupiter.api.Assertions.*;

public class FileIntegrationTest {

    @Test
    public void integrationTestWithFilePositive() throws HowLongAlgorithm.AlgorithmException {
        File input = new File("src/test/resources/forecast1.json");
        WeatherForecastSource source = new JsonFileBasedForecastSource(input);
        HowLongAlgorithm algorithm = new HowLongAlgorithm(source);
        LocalDateTime start = LocalDateTime.of(2024, 8, 22, 13, 21, 16, 0);
        long result = algorithm.calculate(OffsetDateTime.of(start, ZoneId.of("Europe/Oslo").getRules().getOffset(start)));
        assertEquals(result, 6);
    }

    @Test
    public void integrationTestWithFileNegative() throws HowLongAlgorithm.AlgorithmException {
        File input = new File("src/test/resources/forecast2.json");
        WeatherForecastSource source = new JsonFileBasedForecastSource(input);
        HowLongAlgorithm algorithm = new HowLongAlgorithm(source);
        LocalDateTime start = LocalDateTime.of(2024, 8, 22, 19, 1, 5, 0);
            long result = algorithm.calculate(OffsetDateTime.of(start, ZoneId.of("Europe/Oslo").getRules().getOffset(start)));
            assertEquals(result, -14);

    }
}

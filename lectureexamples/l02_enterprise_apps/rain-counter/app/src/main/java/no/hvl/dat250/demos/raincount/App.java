/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package no.hvl.dat250.demos.raincount;

import java.io.File;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;

public class App {

    public static void main(String[] args) {
        // creation
        Presenter presenter = new CommandLinePresenter();
        File f = new File("app/src/test/resources/forecast1.json");
        WeatherForecastSource source =  new JsonFileBasedForecastSource(f);
        HowLongAlgorithm algorithm = new HowLongAlgorithm(source);

        // params
        LocalDateTime questionTime = LocalDateTime.of(2024, 8, 22, 13, 11, 15);
        OffsetDateTime questionTimeZ = OffsetDateTime.of(questionTime, ZoneId.of("Europe/Oslo").getRules().getOffset(questionTime));

        // behaviour
        long result = algorithm.calculate(questionTimeZ);
        presenter.presentResult(result, questionTimeZ);
    }
}

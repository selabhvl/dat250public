package no.hvl.dat250.demos.raincount;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.File;

@Configuration
@ComponentScan("no.hvl.dat250.demos.raincount")
public class Config {

    @Bean
    public WeatherForecastSource forecastBean() {
        return new JsonFileBasedForecastSource(new File("app/src/test/resources/forecast1.json"));
    }


}

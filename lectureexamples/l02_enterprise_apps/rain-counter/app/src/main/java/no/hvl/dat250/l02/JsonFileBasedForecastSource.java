package no.hvl.dat250.l02;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;


import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class JsonFileBasedForecastSource implements WeatherForecastSource {

    private final File jsonFile;

    private final ObjectMapper objectMapper;

    public JsonFileBasedForecastSource(final File jsonFile) {
        this.jsonFile = jsonFile;
        this.objectMapper = new ObjectMapper();
        JavaTimeModule timeModule = new JavaTimeModule();
        objectMapper.registerModule(timeModule);
    }

    @Override
    public Collection<Forecast> getForecast() {
        try {
            return objectMapper.<List<Forecast>>readValue(jsonFile, objectMapper.getTypeFactory().constructCollectionType(List.class, Forecast.class));
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}

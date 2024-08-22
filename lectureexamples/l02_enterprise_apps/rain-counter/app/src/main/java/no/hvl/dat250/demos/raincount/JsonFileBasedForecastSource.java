package no.hvl.dat250.demos.raincount;

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
            List<Forecast> result = objectMapper.readValue(jsonFile, objectMapper.getTypeFactory().constructCollectionType(List.class, Forecast.class));
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}

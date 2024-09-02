package no.hvl.dat250.rest.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

public class ParseLocationforecast {

    private final ObjectMapper mapper;

    public ParseLocationforecast(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public List<Weather.ForecastHour> parseLocationForecastJson(InputStream is) throws IOException {
        JsonNode rootNode = mapper.readTree(is);
        JsonNode timeseriesNode = rootNode.get("properties").get("timeseries");
        List<Weather.ForecastHour> forecasts =  mapper.readValue(timeseriesNode.traverse(), mapper.getTypeFactory().constructCollectionLikeType(List.class, Weather.ForecastHour.class));
        return  forecasts.stream().filter(f -> f.getPrecipitation() != null).collect(Collectors.toList());
    }
}

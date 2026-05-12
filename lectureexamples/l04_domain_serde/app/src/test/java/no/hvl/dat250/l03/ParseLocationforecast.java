package no.hvl.dat250.l03;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import no.hvl.dat250.l04.examples.domains.Weather;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.fail;

public class ParseLocationforecast {

    private static final String TEST_DATA = """
            {
              "time": "2024-08-29T17:00:00Z",
              "data": {
                "instant": {
                  "details": {
                    "air_pressure_at_sea_level": 1011.6,
                    "air_temperature": 15.3,
                    "cloud_area_fraction": 68.7,
                    "relative_humidity": 86.9,
                    "wind_from_direction": 173.8,
                    "wind_speed": 3.2
                  }
                },
                "next_12_hours": {
                  "summary": {
                    "symbol_code": "rain"
                  },
                  "details": {}
                },
                "next_1_hours": {
                  "summary": {
                    "symbol_code": "partlycloudy_day"
                  },
                  "details": {
                    "precipitation_amount": 0.0
                  }
                }
              }
            }
            """;


    @Test
    public void testParse() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonParser parser = mapper.getFactory().createParser(TEST_DATA);
        while (parser.hasCurrentToken()) {

            System.out.println(parser.nextToken());
        }
        fail();
    }


    public static void main(String[] args) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            JsonNode jsonNode = mapper.readTree(ParseLocationforecast.class.getResourceAsStream("/data/weather.json"));

            JsonNode timeseriesNode = jsonNode.get("properties").get("timeseries");
            List<Weather.ForecastHour> forecasts =  mapper.readValue(timeseriesNode.traverse(), mapper.getTypeFactory().constructCollectionLikeType(List.class, Weather.ForecastHour.class));
            List<Weather.ForecastHour> filteres = forecasts.stream().filter(f -> f.getPrecipitation() != null).collect(Collectors.toList());
            for (Weather.ForecastHour f : filteres) {
                System.out.println(f.getHour().atZone(ZoneId.of("Europe/Oslo")).format(DateTimeFormatter.ISO_DATE_TIME) + ": " + f.getPrecipitation());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

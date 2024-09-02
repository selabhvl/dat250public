package no.hvl.dat250.rest.util;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.IOException;
import java.time.Instant;

public class Weather {

    @JsonDeserialize(using = ForecastDeserializer.class)
    public static class ForecastHour {

        private final Instant hour;
        private final Double precipitation;

        public ForecastHour(Instant hour, Double precipitation) {
            this.hour = hour;
            this.precipitation = precipitation;
        }

        public Instant getHour() {
            return hour;
        }

        public Double getPrecipitation() {
            return precipitation;
        }
    }

    public static class ForecastDeserializer extends JsonDeserializer<ForecastHour> {

        private Double readPrecipitation(JsonParser parser) throws IOException {
            int depth = 1;
            Double result = null;
            JsonToken token;
            while (depth > 0) {
                token = parser.nextToken();
                switch (token) {
                    case START_OBJECT -> depth++;
                    case END_OBJECT -> depth--;
                    case FIELD_NAME -> {
                        if ("precipitation_amount".equals(parser.currentName())) {
                            parser.nextToken();
                            if (parser.getText() != null) {
                                result = Double.parseDouble(parser.getText());
                            }
                        }
                    }
                }
            }
            return result;
        }

        @Override
        public ForecastHour deserialize(JsonParser parser, DeserializationContext deserializationContext) throws IOException, JacksonException {
            while (parser.currentToken() != JsonToken.START_OBJECT) {
                parser.nextToken();
            }
            JsonToken token;
            Instant ts = null;
            Double val = null;
            int depth = 1;
            while (depth > 0) {
                token = parser.nextToken();
                switch (token) {
                    case START_OBJECT -> depth++;
                    case END_OBJECT -> depth--;
                    case FIELD_NAME -> {
                        if ("time".equals(parser.currentName())) {
                            parser.nextToken();
                            ts = Instant.parse(parser.getText());
                        } else if ("next_1_hours".equals(parser.currentName())) {
                            parser.nextToken(); //start
                            val = readPrecipitation(parser);
                        }
                    }
                }
            }
            return new ForecastHour(ts, val);

        }
    }

}

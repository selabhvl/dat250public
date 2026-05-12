package no.hvl.dat250.l04.examples.domains;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

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



    public static class Measurement {

        private double temperatureDegreeCelsius;
        private double precipitationMM;

        private double windMps;

        private double windDirectionDegrees;

        public Measurement() {
        }

        public Measurement(double temperatureDegreeCelsius, double precipitationMM, double windMps, double windDirectionDegrees) {
            this.temperatureDegreeCelsius = temperatureDegreeCelsius;
            this.precipitationMM = precipitationMM;
            this.windMps = windMps;
            this.windDirectionDegrees = windDirectionDegrees;
        }

        public double getTemperatureDegreeCelsius() {
            return temperatureDegreeCelsius;
        }

        public void setTemperatureDegreeCelsius(double temperatureDegreeCelsius) {
            this.temperatureDegreeCelsius = temperatureDegreeCelsius;
        }

        public double getPrecipitationMM() {
            return precipitationMM;
        }

        public void setPrecipitationMM(double precipitationMM) {
            this.precipitationMM = precipitationMM;
        }

        public double getWindMps() {
            return windMps;
        }

        public void setWindMps(double windMps) {
            this.windMps = windMps;
        }

        public double getWindDirectionDegrees() {
            return windDirectionDegrees;
        }

        public void setWindDirectionDegrees(double windDirectionDegrees) {
            this.windDirectionDegrees = windDirectionDegrees;
        }
    }

}

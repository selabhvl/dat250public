package no.hvl.dat250.l03;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import no.hvl.dat250.l04.examples.domains.Weather;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This is your first (warm-up) serialization challenge.
 * We start by investigating how you can steer the presentation and ordering of properties
 * in an object.
 * Your objective is to change the naming convention while serializing
 * a class to JSON. The {@link no.hvl.dat250.l04.examples.domains.Weather.Measurement} class
 * is using the camel-case naming convention for naming of its attributes.
 * However, the produced JSON is expected to follow a snake_case naming convention, e.g.
 * because a consumer system requires it...
 */
public class Challenge1 {

    private final ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    private static final String EXPECTED = """
            {
              "temperature_degree_celsius" : 18.1,
              "precipitation_mm" : 0.3,
              "wind_mps" : 3.4,
              "wind_direction_degrees" : 180.0
            }
                        """.trim();

    @Test
    public void renameProperties() throws IOException {
        Weather.Measurement m = new Weather.Measurement(
                18.1,
                0.3,
                3.4,
                180
        );
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        mapper.writeValue(bos, m);

        String actual = bos.toString(StandardCharsets.UTF_8);
        // TODO: make me green without changing the name of the instance variables in Measurement
        // you should only add the correct annotation.
        // Tips: Have a look at @JsonProperty, or you may even get @JsonNaming to work? ...
        assertEquals(EXPECTED, actual.trim());
    }

    // reorder properties




}
